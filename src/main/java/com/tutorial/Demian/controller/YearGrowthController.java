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
import com.tutorial.Demian.dto.YearGrowthDTO;
import com.tutorial.Demian.model.Desire;
import com.tutorial.Demian.model.User;
import com.tutorial.Demian.model.YearGrowth;
import com.tutorial.Demian.service.DesireService;
import com.tutorial.Demian.service.UserService;
import com.tutorial.Demian.service.YearGrowthService;
import com.tutorial.Demian.validator.YearGrowthValidator;

import lombok.Data;

@Controller
@RequestMapping("/years")
public class YearGrowthController {
    public final static int UNDEFINED_YEAR = -1;

    @Autowired
    private UserService userService;
    @Autowired
    private DesireService desireService;
    @Autowired
    private YearGrowthService yearGrowthService;
    @Autowired
    private YearGrowthValidator yearGrowthValidator;

    @GetMapping("/page")
    public String year(Model model, Authentication authentication) {
        User user = userService.get(authentication.getName());

        List<Desire> desires = desireService.getCurrentUserDesires(user.getId());
        YearGrowthController.Response response = yearGrowthService.getYearPageResp(user.getId(), desires, UNDEFINED_YEAR);

        model.addAttribute("response", response);

        return "schedule/year_page";
    }

    @GetMapping("/page/{startYear}")
    public String yearWithStartYear(Model model, @PathVariable int startYear, Authentication authentication) {
        User user = userService.get(authentication.getName());

        List<Desire> desires = desireService.getCurrentUserDesires(user.getId());
        YearGrowthController.Response response = yearGrowthService.getYearPageResp(user.getId(), desires, startYear);

        model.addAttribute("response", response);

        return "schedule/year_page";
    }

    @GetMapping("/form")
    public String jobForm(Model model, @RequestParam(required = true) Long desireId, @RequestParam(required = false) Long jobId) {
        Optional<Desire> mayDesire = desireService.getEntity(desireId);

        if (!mayDesire.isPresent()) {
            // TODO: DO more reasonable exception handling
            return "redirect:/years/page";
        }

        YearGrowthDTO yearGrowthDTO = this.getYearDTO(desireId, jobId);

        model.addAttribute("desire", mayDesire.get());
        model.addAttribute("yearDTO", yearGrowthDTO);

        return "schedule/year_form";
    }

    private YearGrowthDTO getYearDTO(Long desireId, Long jobId) {
        if (jobId == null) {
            YearGrowthDTO yearGrowthDTO = new YearGrowthDTO();
            yearGrowthDTO.setDesireId(desireId);

            return yearGrowthDTO;
        }

        YearGrowth yearGrowth = yearGrowthService.findYear(jobId);
        return YearGrowthDTO.of(yearGrowth);
    }

    @PostMapping("/form")
    public String postJobForm(Model model, @Valid YearGrowthDTO yearGrowthDTO, BindingResult bindingResult,
                              Authentication authentication) {
        Optional<Desire> mayDesire = desireService.getEntity(yearGrowthDTO.getDesireId());
        if (!mayDesire.isPresent()) {
            // TODO: DO more reasonable exception handling
            return "redirect:/years/page";
        }

        model.addAttribute("desire", mayDesire.get());
        model.addAttribute("yearDTO", yearGrowthDTO);

        yearGrowthValidator.validate(yearGrowthDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return "schedule/year_form";
        }

        this.saveYearDTO(mayDesire.get(), yearGrowthDTO);

        return "redirect:/years/page";
    }

    private YearGrowth saveYearDTO(Desire desire, YearGrowthDTO yearGrowthDTO) {
        YearGrowth entity = yearGrowthDTO.getEntity();
        entity.setDesire(desire);

        yearGrowthService.save(entity);

        return entity;
    }

    @Data
    public static class Response {
        private List<YearGrowthController.DesireWithYear> desireWithYears;
        private List<String> timeHeaders;
        private Date startDate;

        public Response() {
            this.desireWithYears = new ArrayList<>();
            this.timeHeaders = new ArrayList<>();
            this.startDate = null;
        }
    }

    @Data
    public static class DesireWithYear {
        private DesireDTO desire;
        private List<YearGrowthDTO> years;

        public DesireWithYear() {
            this.desire = null;
            this.years = new ArrayList<>();
        }
    }
}
