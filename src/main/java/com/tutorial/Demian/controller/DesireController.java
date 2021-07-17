package com.tutorial.Demian.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.tutorial.Demian.dto.DesireDTO;
import com.tutorial.Demian.model.Desire;
import com.tutorial.Demian.model.User;
import com.tutorial.Demian.repository.DesireRepository;
import com.tutorial.Demian.repository.UserRepository;
import com.tutorial.Demian.validator.DesireValidator;


@Controller
@RequestMapping("/desires")
public class DesireController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DesireRepository desireRepository;
    @Autowired
    private DesireValidator desireValidator;

    @GetMapping("/form/{jobType}")
    public String getDesireForm(Model model, @PathVariable int jobType, @RequestParam(required = false) Long id) {
        if (id == null) {
            model.addAttribute("desireDTO", new DesireDTO());
        } else {
            Desire desire = desireRepository.findById(id).orElse(null);
            model.addAttribute("desireDTO", DesireDTO.of(desire));
        }

        model.addAttribute("jobType", jobType);

        return "schedule/desire_form";
    }

    @PostMapping("/form/{jobType}")
    public String postDesireForm(@Valid DesireDTO desireDTO, @PathVariable int jobType, BindingResult bindingResult, Authentication authentication) {
        desireValidator.validate(desireDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            return "schedule/desire_form";
        }

        this.saveDesire(desireDTO, authentication);

        return this.getRedirectPath(jobType);
    }

    private boolean saveDesire(DesireDTO desireDTO, Authentication authentication) {
        try {
            Desire recvDesire = desireDTO.getEntity();

            User user = userRepository.findByUsername(authentication.getName());
            recvDesire.setUser(user);

            desireRepository.save(recvDesire);
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return false;
        }

        return true;
    }

    private String getRedirectPath(int jobType) {
        if (jobType == 0) {
            return "redirect:/decades/page";
        }

        if (jobType == 1) {
            return "redirect:/years/page";
        }

        return "redirect:/months/page";
    }
}
