package com.tutorial.ohDiaraySpringBoot.controller;

import com.tutorial.ohDiaraySpringBoot.dto.DesireWithDecadeJobDTO;
import com.tutorial.ohDiaraySpringBoot.dto.JobDTO;
import com.tutorial.ohDiaraySpringBoot.model.Board;
import com.tutorial.ohDiaraySpringBoot.model.DecadeJob;
import com.tutorial.ohDiaraySpringBoot.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleApiController {
    @Autowired
    private ScheduleService service;

    @GetMapping("/schedules")
    List<DesireWithDecadeJobDTO> all() {
        return service.GetAllDesires();
    }

    @PostMapping("/schedules/job")
    JobDTO newJob(@RequestBody JobDTO jobDTO) {
        // 0 - Decade, 1 - Year, 2 - Month, 3 - Week, 4 - Day
        return service.save(jobDTO);
    }

    @GetMapping("/schedules/job/{id}/{jobType}")
    JobDTO one(@PathVariable Long id, @PathVariable int jobType) {
        return service.get(id, jobType);
//        return repository.findById(id).orElse(null);
    }

    @PutMapping("/schedules/job/{id}/{jobType}")
    JobDTO replaceJob(@RequestBody JobDTO newJob, @PathVariable Long id, @PathVariable int jobType) {
        JobDTO dto = service.get(id, jobType);
        dto.setTitle(newJob.getTitle());
        dto.setContent(newJob.getContent());
        dto.setFromTime(newJob.getFromTime());
        dto.setToTime(newJob.getToTime());

        return service.save(dto);

//        return repository.findById(id)
//                .map(Board -> {
//                    Board.setTitle(newBoard.getTitle());
//                    Board.setContent(newBoard.getContent());
//                    return repository.save(Board);
//                })
//                .orElseGet(() -> {
//                    newBoard.setId(id);
//                    return repository.save(newBoard);
//                });
    }
//
    @DeleteMapping("/schedules/job/{id}/{jobType}")
    void deleteBoard(@PathVariable Long id, @PathVariable int jobType) {
        service.delete(id, jobType);
//        repository.deleteById(id);
    }
}
