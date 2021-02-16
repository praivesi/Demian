package com.tutorial.ohDiaraySpringBoot.controller;

import com.tutorial.ohDiaraySpringBoot.model.Desire;
import com.tutorial.ohDiaraySpringBoot.repository.DesireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private DesireRepository desireRepository;

    @GetMapping("/decade")
    public String decade(Model model) {

        List<Desire> desires = desireRepository.findAll();
        model.addAttribute("desires", desires);

        return "schedule/decade";
    }
}
