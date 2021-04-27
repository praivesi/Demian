package com.tutorial.Demian.controller;

import com.tutorial.Demian.dto.DecadeDTO;
import com.tutorial.Demian.dto.DesireDTO;
import com.tutorial.Demian.model.Decade;
import com.tutorial.Demian.model.Desire;
import com.tutorial.Demian.model.User;
import com.tutorial.Demian.repository.DecadeRepository;
import com.tutorial.Demian.repository.DesireRepository;
import com.tutorial.Demian.repository.UserRepository;
import com.tutorial.Demian.service.DecadeService;
import com.tutorial.Demian.service.DesireService;
import com.tutorial.Demian.validator.DecadeValidator;
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
public class DecadeController {
    public final static int UNDEFINED_DECADE = -1;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DesireRepository desireRepository;
    @Autowired
    private DecadeRepository decadeRepository;
    @Autowired
    private DecadeValidator decadeValidator;
    @Autowired
    private DecadeService decadeService;
    @Autowired
    private DesireService desireService;

    @GetMapping("/page")
    public String decade(Model model, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName());

        List<Desire> desires = desireService.getCurrentUserDesires(user.getId());
        Response response = decadeService.getDecadePageResp(user.getId(), desires, UNDEFINED_DECADE);

        model.addAttribute("response", response);

        return "/schedule/decade_page";
    }

    @GetMapping("/page/{startDecade}")
    public String decadeWithStartYear(Model model, @PathVariable int startDecade, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName());

        List<Desire> desires = desireService.getCurrentUserDesires(user.getId());
        Response response = decadeService.getDecadePageResp(user.getId(), desires, startDecade);

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
            DecadeDTO dto = new DecadeDTO();
            dto.setDesireId(desireId);
            model.addAttribute("decadeJobDTO", dto);
        } else {
            Decade decade = decadeRepository.findById(jobId).orElse(null);
            model.addAttribute("decadeJobDTO", DecadeDTO.of(decade));
        }

        model.addAttribute("desireDTO", mayDesire.get());

        return "/schedule/decade_form";
    }

    @PostMapping("/form")
    public String postDecadeJobForm(Model model, @Valid DecadeDTO decadeDTO, BindingResult bindingResult,
                                    Authentication authentication) {
        Optional<Desire> mayDesire = desireRepository.findById(decadeDTO.getDesireId());
        if (!mayDesire.isPresent()) {
            // TODO: DO more reasonable exception handling
            return "redirect:/decades/page";
        }

        model.addAttribute("desireDTO", mayDesire.get());

        decadeValidator.validate(decadeDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/schedule/decade_form";
        }

        Decade decade = decadeDTO.getEntity();
        decade.setDesire(mayDesire.get());

        decadeRepository.save(decade);

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
        private List<DecadeDTO> decades;

        public DesireWithDecade() {
            this.desire = null;
            this.decades = new ArrayList<>();
        }
    }
}
