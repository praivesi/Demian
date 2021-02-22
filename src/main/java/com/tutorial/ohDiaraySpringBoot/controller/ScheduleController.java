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

        for(int i = 10; i < 110; i++)
        {
            Desire newDesire = new Desire();
        }
        model.addAttribute("desires", desires);

        return "schedule/decade";
    }

    @GetMapping("/year")
    public String year(Model model){
        return "schedule/year";
    }

    @GetMapping("/month")
    public String month(Model model){
        return "schedule/month";
    }

    @GetMapping("/week")
    public String week(Model model){
        return "schedule/week";
    }

    @GetMapping("/day")
    public String day(Model model) {
        return "schedule/day";
    }
}
