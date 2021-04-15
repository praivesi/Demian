package com.tutorial.Demian.controller;

import com.tutorial.Demian.dto.MonthJobDTO;
import com.tutorial.Demian.dto.MonthPageDTO;
import com.tutorial.Demian.dto.YearJobDTO;
import com.tutorial.Demian.dto.YearPageDTO;
import com.tutorial.Demian.model.Desire;
import com.tutorial.Demian.model.MonthJob;
import com.tutorial.Demian.model.User;
import com.tutorial.Demian.model.YearJob;
import com.tutorial.Demian.repository.DesireRepository;
import com.tutorial.Demian.repository.MonthJobRepository;
import com.tutorial.Demian.repository.UserRepository;
import com.tutorial.Demian.repository.YearJobRepository;
import com.tutorial.Demian.service.MonthJobService;
import com.tutorial.Demian.service.YearJobService;
import com.tutorial.Demian.validator.MonthJobValidator;
import com.tutorial.Demian.validator.YearJobValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/months")
public class MonthJobController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DesireRepository desireRepository;
    @Autowired
    private MonthJobRepository monthJobRepository;
    @Autowired
    private MonthJobService monthJobService;
    @Autowired
    private MonthJobValidator monthJobValidator;

    @GetMapping("/page")
    public String month(Model model, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);

        Calendar startCal = new GregorianCalendar();
        startCal.set(Calendar.MONTH, startCal.get(Calendar.MONTH) - 2);
        int startYear = startCal.get(Calendar.YEAR);
        int startMonth = startCal.get(Calendar.MONTH);

        List<MonthPageDTO> monthPageDTOs = monthJobService.get(startCal.getTime(), user.getId());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        List<String> timeHeaders = new ArrayList<>();
        Calendar tmpCal = (Calendar) startCal.clone();

        for (int i = 0; i < 6; i++) {
            tmpCal.add(Calendar.MONTH, 1);
            timeHeaders.add(new SimpleDateFormat("yyyy-MM").format(tmpCal.getTime()));
        }

        model.addAttribute("monthPageDTOs", monthPageDTOs);
        model.addAttribute("timeHeaders", timeHeaders);
        model.addAttribute("startYear", startYear);
        model.addAttribute("startMonth", startMonth);

        return "/schedule/month_page";
    }

    @GetMapping("/page/{startYear}/{startMonth}")
    public String monthWithStartMonth(Model model, @PathVariable int startYear, @PathVariable int startMonth,
                                      Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);

        Calendar startCal = new GregorianCalendar();
        startCal.set(Calendar.YEAR, startYear);
        startCal.set(Calendar.MONTH, startMonth);

        List<MonthPageDTO> monthPageDTOs = monthJobService.get(startCal.getTime(), user.getId());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        List<String> timeHeaders = new ArrayList<>();
        Calendar tmpCal = (Calendar) startCal.clone();

        for (int i = 0; i < 6; i++) {
            tmpCal.add(Calendar.MONTH, 1);
            timeHeaders.add(dateFormat.format(tmpCal.getTime()));
        }

        model.addAttribute("monthPageDTOs", monthPageDTOs);
        model.addAttribute("timeHeaders", timeHeaders);
        model.addAttribute("startYear", startYear);
        model.addAttribute("startMonth", startMonth);

        return "/schedule/month_page";
    }

    @GetMapping("/form")
    public String jobForm(Model model, @RequestParam(required = true) Long desireId, @RequestParam(required = false) Long jobId) {
        Optional<Desire> mayDesire = desireRepository.findById(desireId);

        if (!mayDesire.isPresent()) {
            // TODO: DO more reasonable exception handling
            return "redirect:/months/page";
        }

        if (jobId == null) {
            MonthJobDTO monthDTO = new MonthJobDTO();
            monthDTO.setDesireId(desireId);
            model.addAttribute("monthJobDTO", monthDTO);
        } else {
            MonthJob month = monthJobRepository.findById(jobId).orElse(null);
            model.addAttribute("monthJobDTO", MonthJobDTO.of(month));
        }

        model.addAttribute("desire", mayDesire.get());

        return "schedule/month_form";
    }

    @PostMapping("/form")
    public String postJobForm(Model model, @Valid MonthJobDTO monthJobDTO, BindingResult bindingResult,
                              Authentication authentication) {
        Optional<Desire> mayDesire = desireRepository.findById(monthJobDTO.getDesireId());
        if (!mayDesire.isPresent()) {
            // TODO: DO more reasonable exception handling
            return "redirect:/months/page";
        }

        model.addAttribute("desire", mayDesire.get());
        model.addAttribute("monthJobDTO", monthJobDTO);

        monthJobValidator.validate(monthJobDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/schedule/month_form";
        }

        MonthJob entity = monthJobDTO.getEntity();
        entity.setDesire(mayDesire.get());

        monthJobRepository.save(entity);

        return "redirect:/months/page";
    }
}
