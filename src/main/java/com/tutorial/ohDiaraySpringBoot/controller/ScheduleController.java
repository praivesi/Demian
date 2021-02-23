package com.tutorial.ohDiaraySpringBoot.controller;

import com.tutorial.ohDiaraySpringBoot.model.*;
import com.tutorial.ohDiaraySpringBoot.repository.*;
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
    @Autowired
    private DecadeJobRepository decadeJobRepository;
    @Autowired
    private YearJobRepository yearJobRepository;
    @Autowired
    private MonthJobRepository monthJobRepository;
    @Autowired
    private WeekJobRepository weekJobRepository;
    @Autowired
    private DayJobRepository dayJobRepository;


    @GetMapping("/decade")
    public String decade(Model model) {
        List<Desire> desires = desireRepository.findAll();
        model.addAttribute("desires", desires);

        return "schedule/decade";
    }

    @GetMapping("/year")
    public String year(Model model){
        List<DecadeJob> decadeJobs = decadeJobRepository.findAll();
        model.addAttribute("decadeJobs", decadeJobs);

        return "schedule/year";
    }

    @GetMapping("/month")
    public String month(Model model)
    {
        List<YearJob> yearJobs = yearJobRepository.findAll();
        model.addAttribute("yearJobs", yearJobs);

        return "schedule/month";
    }

    @GetMapping("/week")
    public String week(Model model)
    {
        List<MonthJob> monthJobs = monthJobRepository.findAll();
        model.addAttribute("monthJobs", monthJobs);

        return "schedule/week";
    }

    @GetMapping("/day")
    public String day(Model model)
    {
        List<WeekJob> weekJobs = weekJobRepository.findAll();
        model.addAttribute("weekJobs", weekJobs);

        return "schedule/day";
    }
}
