package com.tutorial.ohDiaraySpringBoot.service;

import com.tutorial.ohDiaraySpringBoot.dto.JobDTO;
import com.tutorial.ohDiaraySpringBoot.model.MonthJob;
import com.tutorial.ohDiaraySpringBoot.model.WeekJob;
import com.tutorial.ohDiaraySpringBoot.repository.MonthJobRepository;
import com.tutorial.ohDiaraySpringBoot.repository.WeekJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WeekJobService {
    @Autowired
    private MonthJobRepository monthJobRepository;
    @Autowired
    private WeekJobRepository weekJobRepository;

    public JobDTO save(JobDTO jobDTO) {
        if (jobDTO.getJobType() != 3) return null;

        Optional<MonthJob> maybeParentJob = monthJobRepository.findById(jobDTO.getParentId());
        if (maybeParentJob.isPresent()) {
            WeekJob newWeekJob = new WeekJob(jobDTO.getTitle(), jobDTO.getContent(), jobDTO.getFromTime(), jobDTO.getToTime(), maybeParentJob.get());
            WeekJob entity = weekJobRepository.save(newWeekJob);
            jobDTO.setId(entity.getId());
        } else {
            jobDTO = new JobDTO();
        }

        return jobDTO;
    }

    public JobDTO get(Long id) {
        JobDTO dto = new JobDTO();
        Optional<WeekJob> maybeWeekJob = weekJobRepository.findById(id);

        if (maybeWeekJob.isPresent()) {
            WeekJob entity = maybeWeekJob.get();

            dto.setJobType(1);
            dto.setId(entity.getId());
            dto.setTitle(entity.getTitle());
            dto.setContent(entity.getContent());
            dto.setFromTime(entity.getFromTime());
            dto.setToTime(entity.getToTime());
            dto.setParentId(entity.getMonthJob().getId());
        }

        return dto;
    }

    public Long delete(Long id) {
        try {
            weekJobRepository.deleteById(id);
        } catch (Exception e) {
            id = -1l;

        }

        return id;
    }
}
