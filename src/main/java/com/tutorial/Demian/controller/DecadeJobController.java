package com.tutorial.Demian.controller;

import com.tutorial.Demian.dto.DecadeJobDTO;
import com.tutorial.Demian.dto.DesireDTO;
import com.tutorial.Demian.model.DecadeJob;
import com.tutorial.Demian.model.Desire;
import com.tutorial.Demian.model.User;
import com.tutorial.Demian.repository.DecadeJobRepository;
import com.tutorial.Demian.repository.DesireRepository;
import com.tutorial.Demian.repository.UserRepository;
import com.tutorial.Demian.service.DecadeJobService;
import com.tutorial.Demian.service.DesireService;
import com.tutorial.Demian.validator.DecadeJobValidator;
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
@RequestMapping("/decades")
public class DecadeJobController {
    public final static int UNDEFINED_DECADE = -1;

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
    @Autowired
    private DesireService desireService;

    @GetMapping("/page")
    public String decade(Model model, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName());

        List<Desire> desires = desireService.getCurrentUserDesires(user.getId());
        Response response = decadeJobService.getDecadePageResp(user.getId(), desires, UNDEFINED_DECADE);

        model.addAttribute("response", response);

        return "/schedule/decade_page";
    }

    @GetMapping("/page/{startDecade}")
    public String decadeWithStartYear(Model model, @PathVariable int startDecade, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName());

        List<Desire> desires = desireService.getCurrentUserDesires(user.getId());
        Response response = decadeJobService.getDecadePageResp(user.getId(), desires, startDecade);

        model.addAttribute("response", response);

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

    @Data
    public static class Response {
        private List<DesireWithDecade> desireWithDecades;
        private List<String> timeHeaders;
        private Date startDate;

        public Response(){
            this.desireWithDecades = new ArrayList<>();
            this.timeHeaders = new ArrayList<>();
            this.startDate = null;
        }
    }

    @Data
    public static class DesireWithDecade {
        private DesireDTO desire;
        private List<DecadeJobDTO> decades;

        public DesireWithDecade() {
            this.desire = null;
            this.decades = new ArrayList<>();
        }
    }
}
