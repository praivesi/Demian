package com.tutorial.Demian.controller;

import com.tutorial.Demian.dto.DesireDTO;
import com.tutorial.Demian.model.Desire;
import com.tutorial.Demian.model.User;
import com.tutorial.Demian.repository.DesireRepository;
import com.tutorial.Demian.repository.UserRepository;
import com.tutorial.Demian.validator.DesireValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/desires")
public class DesireController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DesireRepository desireRepository;
    @Autowired
    private DesireValidator desireValidator;

    @GetMapping("/form")
    public String getDesireForm(Model model, @RequestParam(required = false) Long id) {
        if (id == null) {
            model.addAttribute("desireDTO", new DesireDTO());
        } else {
            Desire desire = desireRepository.findById(id).orElse(null);
            model.addAttribute("desireDTO", DesireDTO.of(desire));
        }
        return "/schedule/desire_form";
    }

    @PostMapping("/form")
    public String postDesireForm(@Valid DesireDTO desireDTO, BindingResult bindingResult, Authentication authentication) {
        desireValidator.validate(desireDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/schedule/desire_form";
        }

        Desire recvDesire = desireDTO.getEntity();

        User user = userRepository.findByUsername("hsoh");
        recvDesire.setUser(user);

        desireRepository.save(recvDesire);

        return "redirect:/decades/page";
    }
}
