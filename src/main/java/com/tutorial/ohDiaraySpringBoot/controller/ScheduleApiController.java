package com.tutorial.ohDiaraySpringBoot.controller;

import com.tutorial.ohDiaraySpringBoot.dto.DesireDTO;
import com.tutorial.ohDiaraySpringBoot.dto.DesireWithDecadeJobDTO;
import com.tutorial.ohDiaraySpringBoot.dto.JobDTO;
import com.tutorial.ohDiaraySpringBoot.service.DesireService;
import com.tutorial.ohDiaraySpringBoot.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleApiController {
    private DesireService desireService;
    private JobService jobService;

    @Autowired
    public ScheduleApiController(
            DesireService desireService,
            JobService jobService){
        this.desireService = desireService;
        this.jobService = jobService;
    }


    @GetMapping("/schedules")
    List<DesireWithDecadeJobDTO> all() {
        return desireService.GetAllDesires();
    }

    @PostMapping("/schedules/desire")
    DesireDTO addDesire(@RequestBody DesireDTO desireDTO) {
        return desireService.save(desireDTO);
    }

    @GetMapping("/schedules/desire/{id}")
    DesireDTO getDesire(@PathVariable Long id) {
        return desireService.get(id);
    }

    @PutMapping("/schedules/desire/{id}")
    DesireDTO updateDesire(@RequestBody DesireDTO dto, @PathVariable Long id) {
        return desireService.update(dto, id);
    }

    @DeleteMapping("/schedules/desire/{id}")
    Long deleteDesire(@PathVariable Long id) {
        return desireService.delete(id);
    }

    @PostMapping("/schedules/job")
    JobDTO newJob(@RequestBody JobDTO jobDTO) {
        // 0 - Decade, 1 - Year, 2 - Month, 3 - Week, 4 - Day
        return jobService.save(jobDTO);
    }

    @GetMapping("/schedules/job/{id}/{jobType}")
    JobDTO getJob(@PathVariable Long id, @PathVariable int jobType) {
        return jobService.get(id, jobType);
    }

    @PutMapping("/schedules/job/{id}/{jobType}")
    JobDTO replaceJob(@RequestBody JobDTO dto, @PathVariable Long id, @PathVariable int jobType) {
        return jobService.update(dto, id, jobType);
    }

    @DeleteMapping("/schedules/job/{id}/{jobType}")
    Long deleteJob(@PathVariable Long id, @PathVariable int jobType) {
        return jobService.delete(id, jobType);
    }
}
