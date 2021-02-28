package com.tutorial.ohDiaraySpringBoot.controller;

import com.tutorial.ohDiaraySpringBoot.model.*;
import com.tutorial.ohDiaraySpringBoot.repository.*;
import com.tutorial.ohDiaraySpringBoot.service.DecadeJobService;
import com.tutorial.ohDiaraySpringBoot.service.DesireService;
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

    @Autowired
    private DesireService desireService;
    @Autowired
    private DecadeJobService decadeJobService;


    @GetMapping("/decade")
    public String decade(Model model) {
        List<Desire> desires = desireRepository.findAll();
        model.addAttribute("desires", desires);
        model.addAttribute("clickedDesire", new Desire());
        model.addAttribute("clickedDecadeJob", new DecadeJob());

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

    @PostMapping("/decade")
    public String postForm(@Valid DecadeJob decadeJob, BindingResult bindingResult, Authentication authentication){
        // boardValidator.validate(board, bindingResult);

       if(bindingResult.hasErrors()) {
           return "schedule/decade";
       }

//       String username = authentication.getName();

//         boardService.save(username, board);
        decadeJobService.save("hsoh", decadeJob);

       return "redirect:/schedule/decade";
    }

    @PostMapping("/desire")
    public  String postForm(@Valid Desire desire, BindingResult bindingResult, Authentication authentication){
        if(bindingResult.hasErrors()){
            return "schedule/decade";
        }

//        String username = authentication.getName();

//        desireService.save(username, desire);
        desireService.save("hsoh", desire);

        return "redirect:/schedule/decade";
    }
}
