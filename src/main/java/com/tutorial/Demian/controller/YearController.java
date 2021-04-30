package com.tutorial.Demian.controller;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.tutorial.Demian.dto.DesireDTO;
import com.tutorial.Demian.dto.YearDTO;
import com.tutorial.Demian.model.Desire;
import com.tutorial.Demian.model.User;
import com.tutorial.Demian.model.Year;
import com.tutorial.Demian.service.DesireService;
import com.tutorial.Demian.service.UserService;
import com.tutorial.Demian.service.YearService;
import com.tutorial.Demian.validator.YearValidator;

import lombok.Data;

@Controller
@RequestMapping("/years")
public class YearController {
    public final static int UNDEFINED_YEAR = -1;

    @Autowired private UserService userService;
    @Autowired private DesireService desireService;
    @Autowired private YearService yearService;
    @Autowired private YearValidator yearValidator;

    @GetMapping("/page")
    public String year(Model model, Authentication authentication) {
        User user = userService.get(authentication.getName());

        List<Desire> desires = desireService.getCurrentUserDesires(user.getId());
        YearController.Response response = yearService.getYearPageResp(user.getId(), desires, UNDEFINED_YEAR);

        model.addAttribute("response", response);

        return "/schedule/year_page";
    }

    @GetMapping("/page/{startYear}")
    public String yearWithStartYear(Model model, @PathVariable int startYear, Authentication authentication) {
        User user = userService.get(authentication.getName());

        List<Desire> desires = desireService.getCurrentUserDesires(user.getId());
        YearController.Response response = yearService.getYearPageResp(user.getId(), desires, startYear);

        model.addAttribute("response", response);

        return "/schedule/year_page";
    }

    @GetMapping("/form")
    public String jobForm(Model model, @RequestParam(required = true) Long desireId, @RequestParam(required = false) Long jobId) {
        Optional<Desire> mayDesire = desireService.getEntity(desireId);

        if (!mayDesire.isPresent()) {
            // TODO: DO more reasonable exception handling
            return "redirect:/years/page";
        }

        if (jobId == null) {
            YearDTO yearDTO = new YearDTO();
            yearDTO.setDesireId(desireId);
            model.addAttribute("yearJobDTO", yearDTO);
        } else {
            Year year = yearService.findYear(jobId);
            model.addAttribute("yearJobDTO", com.tutorial.Demian.dto.YearDTO.of(year));
        }

        model.addAttribute("desire", mayDesire.get());

        return "schedule/year_form";
    }

    @PostMapping("/form")
    public String postJobForm(Model model, @Valid YearDTO yearDTO, BindingResult bindingResult,
                              Authentication authentication) {
        Optional<Desire> mayDesire = desireService.getEntity(yearDTO.getDesireId());
        if (!mayDesire.isPresent()) {
            // TODO: DO more reasonable exception handling
            return "redirect:/years/page";
        }

        model.addAttribute("desire", mayDesire.get());
        model.addAttribute("yearJobDTO", yearDTO);

        yearValidator.validate(yearDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/schedule/year_form";
        }

        Year entity = yearDTO.getEntity();
        entity.setDesire(mayDesire.get());

        yearService.save(entity);

        return "redirect:/years/page";
    }

    @Data
    public static class Response {
        private List<YearController.DesireWithYear> desireWithYears;
        private List<String> timeHeaders;
        private Date startDate;

        public Response(){
            this.desireWithYears = new ArrayList<>();
            this.timeHeaders = new ArrayList<>();
            this.startDate = null;
        }
    }

    @Data
    public static class DesireWithYear {
        private DesireDTO desire;
        private List<YearDTO> years;

        public DesireWithYear() {
            this.desire = null;
            this.years = new ArrayList<>();
        }
    }
}
