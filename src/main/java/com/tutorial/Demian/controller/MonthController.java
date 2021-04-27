package com.tutorial.Demian.controller;

import com.tutorial.Demian.dto.*;
import com.tutorial.Demian.model.Desire;
import com.tutorial.Demian.model.Month;
import com.tutorial.Demian.model.User;
import com.tutorial.Demian.repository.DesireRepository;
import com.tutorial.Demian.repository.MonthRepository;
import com.tutorial.Demian.repository.UserRepository;
import com.tutorial.Demian.service.DesireService;
import com.tutorial.Demian.service.MonthService;
import com.tutorial.Demian.validator.MonthValidator;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/months")
public class MonthController {
    public final static int UNDEFINED_YEAR = -1;
    public final static int UNDEFINED_MONTH = -1;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DesireRepository desireRepository;
    @Autowired
    private MonthRepository monthRepository;
    @Autowired
    private MonthService monthService;
    @Autowired
    private DesireService desireService;
    @Autowired
    private MonthValidator monthValidator;

    @GetMapping("/page")
    public String month(Model model, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName());

        List<Desire> desires = desireService.getCurrentUserDesires(user.getId());
        MonthController.Response response = monthService.getMonthPageResp(user.getId(), desires, UNDEFINED_YEAR, UNDEFINED_MONTH);

        model.addAttribute("response", response);

        return "/schedule/month_page";
    }

    @GetMapping("/page/{startYear}/{startMonth}")
    public String monthWithStartMonth(Model model, @PathVariable int startYear, @PathVariable int startMonth,
                                      Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName());

        List<Desire> desires = desireService.getCurrentUserDesires(user.getId());
        MonthController.Response response = monthService.getMonthPageResp(user.getId(), desires, startYear, startMonth);

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
            MonthDTO monthDTO = new MonthDTO();
            monthDTO.setDesireId(desireId);
            model.addAttribute("monthJobDTO", monthDTO);
        } else {
            Month month = monthRepository.findById(jobId).orElse(null);
            model.addAttribute("monthJobDTO", MonthDTO.of(month));
        }

        model.addAttribute("desire", mayDesire.get());

        return "schedule/month_form";
    }

    @PostMapping("/form")
    public String postJobForm(Model model, @Valid MonthDTO monthDTO, BindingResult bindingResult,
                              Authentication authentication) {
        Optional<Desire> mayDesire = desireRepository.findById(monthDTO.getDesireId());
        if (!mayDesire.isPresent()) {
            // TODO: DO more reasonable exception handling
            return "redirect:/months/page";
        }

        model.addAttribute("desire", mayDesire.get());
        model.addAttribute("monthJobDTO", monthDTO);

        monthValidator.validate(monthDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/schedule/month_form";
        }

        Month entity = monthDTO.getEntity();
        entity.setDesire(mayDesire.get());

        monthRepository.save(entity);

        return "redirect:/months/page";
    }

    @Data
    public static class Response {
        private List<MonthController.DesireWithMonth> desireWithMonths;
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
        private List<MonthDTO> months;

        public DesireWithMonth() {
            this.desire = null;
            this.months = new ArrayList<>();
        }
    }
}
