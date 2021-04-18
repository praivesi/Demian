package com.tutorial.Demian.controller;

import com.tutorial.Demian.dto.*;
import com.tutorial.Demian.model.Desire;
import com.tutorial.Demian.model.MonthJob;
import com.tutorial.Demian.model.User;
import com.tutorial.Demian.model.YearJob;
import com.tutorial.Demian.repository.DesireRepository;
import com.tutorial.Demian.repository.MonthJobRepository;
import com.tutorial.Demian.repository.UserRepository;
import com.tutorial.Demian.repository.YearJobRepository;
import com.tutorial.Demian.service.DesireService;
import com.tutorial.Demian.service.MonthJobService;
import com.tutorial.Demian.service.YearJobService;
import com.tutorial.Demian.validator.MonthJobValidator;
import com.tutorial.Demian.validator.YearJobValidator;
import lombok.Data;
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
    public final static int UNDEFINED_YEAR = -1;
    public final static int UNDEFINED_MONTH = -1;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DesireRepository desireRepository;
    @Autowired
    private MonthJobRepository monthJobRepository;
    @Autowired
    private MonthJobService monthJobService;
    @Autowired
    private DesireService desireService;
    @Autowired
    private MonthJobValidator monthJobValidator;

    @GetMapping("/page")
    public String month(Model model, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName());

        List<Desire> desires = desireService.getCurrentUserDesires(user.getId());
        MonthJobController.Response response = monthJobService.getMonthPageResp(user.getId(), desires, UNDEFINED_YEAR, UNDEFINED_MONTH);

        model.addAttribute("response", response);

        return "/schedule/month_page";
    }

    @GetMapping("/page/{startYear}/{startMonth}")
    public String monthWithStartMonth(Model model, @PathVariable int startYear, @PathVariable int startMonth,
                                      Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName());

        List<Desire> desires = desireService.getCurrentUserDesires(user.getId());
        MonthJobController.Response response = monthJobService.getMonthPageResp(user.getId(), desires, startYear, startMonth);

        model.addAttribute("response", response);

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

    @Data
    public static class Response {
        private List<MonthJobController.DesireWithMonth> desireWithMonths;
        private List<String> timeHeaders;
        private Date startDate;

        public Response(){
            this.desireWithMonths = new ArrayList<>();
            this.timeHeaders = new ArrayList<>();
            this.startDate = null;
        }
    }

    @Data
    public static class DesireWithMonth {
        private DesireDTO desire;
        private List<MonthJobDTO> months;

        public DesireWithMonth() {
            this.desire = null;
            this.months = new ArrayList<>();
        }
    }
}
