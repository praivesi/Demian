package com.tutorial.ohDiaraySpringBoot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.tutorial.ohDiaraySpringBoot.model.*;
import com.tutorial.ohDiaraySpringBoot.repository.*;
import com.tutorial.ohDiaraySpringBoot.service.DecadeJobService;
import com.tutorial.ohDiaraySpringBoot.service.DesireService;
import com.tutorial.ohDiaraySpringBoot.validator.DecadeJobValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private DecadeJobValidator decadeJobValidator;


    @GetMapping("/decade")
    public String decade(Model model) throws JsonProcessingException {
        Map<Desire, List<DecadeJob>> scheduleMap = new HashMap<>();

        List<String> timeTitles = new ArrayList<>();
        timeTitles.add("2000s");
        timeTitles.add("2010s");
        timeTitles.add("2020s");
        timeTitles.add("2030s");
        timeTitles.add("2040s");

        List<Desire> desires = desireRepository.findAll();

        for (Desire desire : desires)
        {
            List<DecadeJob> orgDecadeJobs = desire.getDecadeJobs();

            List<DecadeJob> sequencedDecadeJobs = new ArrayList<>();
            for (int i = 0; i < timeTitles.size(); i++)
            {
                if(orgDecadeJobs.size() > i)
                {
                    sequencedDecadeJobs.add(orgDecadeJobs.get(i));
                }
                else
                {
                    DecadeJob emptyDecadeJob = new DecadeJob();
                    emptyDecadeJob.setTitle("DEFAULT");
                    sequencedDecadeJobs.add(emptyDecadeJob);
                }
            }

            scheduleMap.put(desire, sequencedDecadeJobs);
        }

        Map<Long, List<DecadeJob>> scheduleMapWithNoChilren = new HashMap<>();

        for(Map.Entry<Desire, List<DecadeJob>> schedule : scheduleMap.entrySet())
        {
            List<DecadeJob> decadesWithNoChildren = new ArrayList<DecadeJob>();
            for(DecadeJob decadeJob : schedule.getValue())
            {
                DecadeJob job = new DecadeJob();
                job.setId(decadeJob.getId());
                job.setTitle(decadeJob.getTitle());
                job.setContent(decadeJob.getContent());
                job.setFromTime(decadeJob.getFromTime());
                job.setToTime(decadeJob.getToTime());
                job.setDesire(null);
                job.setYearJobs(null);

                decadesWithNoChildren.add(job);
            }

            scheduleMapWithNoChilren.put(schedule.getKey().getId(), decadesWithNoChildren);
        }

        Gson gson = new Gson();
        String scheduleMapJson = gson.toJson(scheduleMapWithNoChilren);

        model.addAttribute("timeTitles", timeTitles);
        //model.addAttribute("scheduleMap", scheduleMap);
        model.addAttribute("scheduleMap", scheduleMap);
        model.addAttribute("scheduleMapJson", scheduleMapJson);
        model.addAttribute("desires", desires);
        model.addAttribute("addedDecadeJob", new DecadeJob());
        model.addAttribute("curDesire", desires.get(0));

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
        decadeJobValidator.validate(decadeJob, bindingResult);

       if(bindingResult.hasErrors()) {
           // [210308] TODO: Fix decade.html when ScheduleCotroller returns error
           return "schedule/decade";
       }

       String username = authentication.getName();

        decadeJobService.save(username, decadeJob);

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
