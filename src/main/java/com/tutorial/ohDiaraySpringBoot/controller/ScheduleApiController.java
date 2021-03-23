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

    @GetMapping("/schedule")
    List<DesireWithDecadeJobDTO> all() {
        return service.GetAllDesires();
    }

    @PostMapping("/schedule/job")
    JobDTO newJob(@RequestBody JobDTO jobDTO) {
        // 0 - Decade, 1 - Year, 2 - Month, 3 - Week, 4 - Day
        return service.save(jobDTO);
    }

//    @GetMapping("/boards/{id}")
//    Board one(@PathVariable Long id) {
//        return repository.findById(id).orElse(null);
//    }
//
//    @PutMapping("/boards/{id}")
//    Board replaceBoard(@RequestBody Board newBoard, @PathVariable Long id) {
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
//    }
//
//    @DeleteMapping("/boards/{id}")
//    void deleteBoard(@PathVariable Long id) {
//        repository.deleteById(id);
//    }
}
