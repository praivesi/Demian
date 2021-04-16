package com.tutorial.Demian.controller;

import com.tutorial.Demian.dto.DecadeJobDTO;
import com.tutorial.Demian.dto.DecadeNewDTO;
import com.tutorial.Demian.dto.YearJobDTO;
import com.tutorial.Demian.dto.YearPageDTO;
import com.tutorial.Demian.model.DecadeJob;
import com.tutorial.Demian.model.Desire;
import com.tutorial.Demian.model.User;
import com.tutorial.Demian.model.YearJob;
import com.tutorial.Demian.repository.DesireRepository;
import com.tutorial.Demian.repository.UserRepository;
import com.tutorial.Demian.repository.YearJobRepository;
import com.tutorial.Demian.service.DesireService;
import com.tutorial.Demian.service.YearJobService;
import com.tutorial.Demian.validator.YearJobValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/years")
public class YearJobController {
    @Autowired
    private DesireRepository desireRepository;
    @Autowired
    private DesireService desireService;
    @Autowired
    private YearJobRepository yearJobRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private YearJobService yearJobService;
    @Autowired
    private YearJobValidator yearJobValidator;

    @GetMapping("/page")
    public String year(Model model, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);

        Calendar startCal = new GregorianCalendar();
        startCal.set(Calendar.YEAR, startCal.get(Calendar.YEAR) - 2);

        List<YearPageDTO> yearPageDTOs = yearJobService.get(startCal.getTime(), user.getId());

        List<String> timeHeaders = new ArrayList<>();
        Calendar tmpCal = (Calendar) startCal.clone();

        for (int i = 0; i < 5; i++) {
            tmpCal.add(Calendar.YEAR, 1);
            timeHeaders.add(new SimpleDateFormat("yyyy").format(tmpCal.getTime()));
        }

        model.addAttribute("yearPageDTOs", yearPageDTOs);
        model.addAttribute("timeHeaders", timeHeaders);
        model.addAttribute("startDate", startCal.getTime());

        return "/schedule/year_page";
    }

    @GetMapping("/page/{startYear}")
    public String yearWithStartYear(Model model, @PathVariable int startYear, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);

        Calendar startCal = new GregorianCalendar();
        startCal.set(Calendar.YEAR, startYear);

        List<YearPageDTO> yearPageDTOs = yearJobService.get(startCal.getTime(), user.getId());

        List<String> timeHeaders = new ArrayList<>();
        Calendar tmpCal = (Calendar) startCal.clone();

        for (int i = 0; i < 5; i++) {
            tmpCal.add(Calendar.YEAR, 1);
            timeHeaders.add(new SimpleDateFormat("yyyy").format(tmpCal.getTime()));
        }

        model.addAttribute("yearPageDTOs", yearPageDTOs);
        model.addAttribute("timeHeaders", timeHeaders);
        model.addAttribute("startDate", startCal.getTime());

        return "/schedule/year_page";
    }

    @GetMapping("/form")
    public String jobForm(Model model, @RequestParam(required = true) Long desireId, @RequestParam(required = false) Long jobId) {
        Optional<Desire> mayDesire = desireRepository.findById(desireId);

        if (!mayDesire.isPresent()) {
            // TODO: DO more reasonable exception handling
            return "redirect:/years/page";
        }

        if (jobId == null) {
            YearJobDTO yearDTO = new YearJobDTO();
            yearDTO.setDesireId(desireId);
            model.addAttribute("yearJobDTO", yearDTO);
        } else {
            YearJob year = yearJobRepository.findById(jobId).orElse(null);
            model.addAttribute("yearJobDTO", YearJobDTO.of(year));
        }

        model.addAttribute("desire", mayDesire.get());

        return "schedule/year_form";
    }

    @PostMapping("/form")
    public String postJobForm(Model model, @Valid YearJobDTO yearJobDTO, BindingResult bindingResult,
                                    Authentication authentication) {
        Optional<Desire> mayDesire = desireRepository.findById(yearJobDTO.getDesireId());
        if (!mayDesire.isPresent()) {
            // TODO: DO more reasonable exception handling
            return "redirect:/years/page";
        }

        model.addAttribute("desire", mayDesire.get());
        model.addAttribute("yearJobDTO", yearJobDTO);

        yearJobValidator.validate(yearJobDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/schedule/year_form";
        }

        YearJob entity = yearJobDTO.getEntity();
        entity.setDesire(mayDesire.get());

        yearJobRepository.save(entity);

        return "redirect:/years/page";
    }
}
