package com.tutorial.Demian.controller;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.tutorial.Demian.dto.DesireDTO;
import com.tutorial.Demian.dto.MonthGrowthDTO;
import com.tutorial.Demian.model.Desire;
import com.tutorial.Demian.model.MonthGrowth;
import com.tutorial.Demian.model.User;
import com.tutorial.Demian.service.DesireService;
import com.tutorial.Demian.service.MonthService;
import com.tutorial.Demian.service.UserService;
import com.tutorial.Demian.validator.MonthValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import lombok.Data;

@Controller
@RequestMapping("/months")
public class MonthController {
    public final static int UNDEFINED_YEAR = -1;
    public final static int UNDEFINED_MONTH = -1;

    @Autowired
    private UserService userService;
    @Autowired
    private DesireService desireService;
    @Autowired
    private MonthService monthService;
    @Autowired
    private MonthValidator monthValidator;

    @GetMapping("/page")
    public String month(Model model, Authentication authentication) {
        User user = userService.get(authentication.getName());

        List<Desire> desires = desireService.getCurrentUserDesires(user.getId());
        MonthController.Response response = monthService.getMonthPageResp(user.getId(), desires, UNDEFINED_YEAR, UNDEFINED_MONTH);

        model.addAttribute("response", response);

        return "schedule/month_page";
    }

    @GetMapping("/page/{startYear}/{startMonth}")
    public String monthWithStartMonth(Model model, @PathVariable int startYear, @PathVariable int startMonth,
                                      Authentication authentication) {
        User user = userService.get(authentication.getName());

        List<Desire> desires = desireService.getCurrentUserDesires(user.getId());
        MonthController.Response response = monthService.getMonthPageResp(user.getId(), desires, startYear, startMonth);

        model.addAttribute("response", response);

        return "schedule/month_page";
    }

    @GetMapping("/form")
    public String jobForm(Model model, @RequestParam(required = true) Long desireId, @RequestParam(required = false) Long jobId) {
        Optional<Desire> mayDesire = desireService.getEntity(desireId);

        if (!mayDesire.isPresent()) {
            // TODO: DO more reasonable exception handling
            return "redirect:/months/page";
        }

        MonthGrowthDTO monthGrowthDTO = this.getMonthDTO(desireId, jobId);

        model.addAttribute("monthDTO", monthGrowthDTO);
        model.addAttribute("desire", mayDesire.get());

        return "schedule/month_form";
    }

    private MonthGrowthDTO getMonthDTO(Long desireId, Long jobId) {
        if (jobId == null) {
            MonthGrowthDTO monthGrowthDTO = new MonthGrowthDTO();
            monthGrowthDTO.setDesireId(desireId);

            return monthGrowthDTO;
        }

        MonthGrowth monthGrowth = monthService.getEntity(jobId);

        return MonthGrowthDTO.of(monthGrowth);
    }

    @PostMapping("/form")
    public String postJobForm(Model model, @Valid MonthGrowthDTO monthGrowthDTO, BindingResult bindingResult,
                              Authentication authentication) {
        Optional<Desire> mayDesire = desireService.getEntity(monthGrowthDTO.getDesireId());
        if (!mayDesire.isPresent()) {
            // TODO: DO more reasonable exception handling
            return "redirect:/months/page";
        }

        model.addAttribute("desire", mayDesire.get());
        model.addAttribute("monthDTO", monthGrowthDTO);

        monthValidator.validate(monthGrowthDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/schedule/month_form";
        }

        this.saveMonthDTO(mayDesire.get(), monthGrowthDTO);

        return "redirect:/months/page";
    }

    private MonthGrowth saveMonthDTO(Desire desire, MonthGrowthDTO monthGrowthDTO) {
        MonthGrowth entity = monthGrowthDTO.getEntity();
        entity.setDesire(desire);

        monthService.save(entity);

        return entity;
    }

    @Data
    public static class Response {
        private List<MonthController.DesireWithMonth> desireWithMonths;
        private List<String> timeHeaders;
        private Date startDate;

        public Response() {
            this.desireWithMonths = new ArrayList<>();
            this.timeHeaders = new ArrayList<>();
            this.startDate = null;
        }
    }

    @Data
    public static class DesireWithMonth {
        private DesireDTO desire;
        private List<MonthGrowthDTO> months;

        public DesireWithMonth() {
            this.desire = null;
            this.months = new ArrayList<>();
        }
    }
}
