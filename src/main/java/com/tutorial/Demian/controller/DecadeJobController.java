package com.tutorial.Demian.controller;

import com.tutorial.Demian.dto.DecadeJobDTO;
import com.tutorial.Demian.dto.DecadeNewDTO;
import com.tutorial.Demian.model.DecadeJob;
import com.tutorial.Demian.model.Desire;
import com.tutorial.Demian.model.User;
import com.tutorial.Demian.repository.DecadeJobRepository;
import com.tutorial.Demian.repository.DesireRepository;
import com.tutorial.Demian.repository.UserRepository;
import com.tutorial.Demian.service.DecadeJobService;
import com.tutorial.Demian.validator.DecadeJobValidator;
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
@RequestMapping("/decades")
public class DecadeJobController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DesireRepository desireRepository;
    @Autowired
    private DecadeJobRepository decadeJobRepository;
    @Autowired
    private DecadeJobValidator decadeJobValidator;
    @Autowired
    private DecadeJobService decadeJobService;

    @GetMapping("/page")
    public String decade(Model model, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);

        Calendar startCal = new GregorianCalendar();
        startCal.set(Calendar.YEAR, startCal.get(Calendar.YEAR) - 20 - startCal.get(Calendar.YEAR) % 10);

        List<DecadeNewDTO> decadeNewDTOs = decadeJobService.get(startCal.getTime(), user.getId());

        List<String> timeHeaders = new ArrayList<>();
        Calendar endCal = (Calendar) startCal.clone();

        Calendar beginCal = (Calendar)endCal.clone();
        for (int i = 0; i < 5; i++) {
            endCal.add(Calendar.YEAR, 9);
            timeHeaders.add(String.format(
                    "%s ~ %s",
                    new SimpleDateFormat("yyyy").format(beginCal.getTime()),
                    new SimpleDateFormat("yyyy").format(endCal.getTime())));
            beginCal.add(Calendar.YEAR, 10);
            endCal.add(Calendar.YEAR, 1);
        }

        model.addAttribute("decadeNewDTOs", decadeNewDTOs);
        model.addAttribute("timeHeaders", timeHeaders);
        model.addAttribute("startDate", startCal.getTime());

        return "/schedule/decade_page";
    }

    @GetMapping("/page/{startYear}")
    public String decadeWithStartYear(Model model, @PathVariable int startYear, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);

        Calendar startCal = new GregorianCalendar();
        startCal.set(Calendar.YEAR, startYear);

        List<DecadeNewDTO> decadeNewDTOs = decadeJobService.get(startCal.getTime(), user.getId());

        List<String> timeHeaders = new ArrayList<>();
        Calendar endCal = (Calendar) startCal.clone();

        Calendar beginCal = (Calendar)endCal.clone();
        for (int i = 0; i < 5; i++) {
            endCal.add(Calendar.YEAR, 9);
            timeHeaders.add(String.format(
                    "%s ~ %s",
                    new SimpleDateFormat("yyyy").format(beginCal.getTime()),
                    new SimpleDateFormat("yyyy").format(endCal.getTime())));
            beginCal.add(Calendar.YEAR, 10);
            endCal.add(Calendar.YEAR, 1);
        }

        model.addAttribute("decadeNewDTOs", decadeNewDTOs);
        model.addAttribute("timeHeaders", timeHeaders);
        model.addAttribute("startDate", startCal.getTime());

        return "/schedule/decade_page";
    }

    @GetMapping("/form")
    public String jobForm(Model model, @RequestParam(required = true) Long desireId, @RequestParam(required = false) Long jobId) {
        Optional<Desire> mayDesire = desireRepository.findById(desireId);

        if (!mayDesire.isPresent()) {
            // TODO: DO more reasonable exception handling
            return "redirect:/decades/page";
        }

        if (jobId == null) {
            DecadeJobDTO dto = new DecadeJobDTO();
            dto.setDesireId(desireId);
            model.addAttribute("decadeJobDTO", dto);
        } else {
            DecadeJob decadeJob = decadeJobRepository.findById(jobId).orElse(null);
            model.addAttribute("decadeJobDTO", DecadeJobDTO.of(decadeJob));
        }

        model.addAttribute("desireDTO", mayDesire.get());

        return "/schedule/decade_form";
    }

    @PostMapping("/form")
    public String postDecadeJobForm(Model model, @Valid DecadeJobDTO decadeJobDTO, BindingResult bindingResult,
                                    Authentication authentication) {
        Optional<Desire> mayDesire = desireRepository.findById(decadeJobDTO.getDesireId());
        if (!mayDesire.isPresent()) {
            // TODO: DO more reasonable exception handling
            return "redirect:/decades/page";
        }

        model.addAttribute("desireDTO", mayDesire.get());

        decadeJobValidator.validate(decadeJobDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/schedule/decade_form";
        }

        DecadeJob decadeJob = decadeJobDTO.getEntity();
        decadeJob.setDesire(mayDesire.get());

        decadeJobRepository.save(decadeJob);

        return "redirect:/decades/page";
    }
}
