package com.tutorial.ohDiaraySpringBoot.service;

import com.tutorial.ohDiaraySpringBoot.dto.JobDTO;
import com.tutorial.ohDiaraySpringBoot.model.MonthJob;
import com.tutorial.ohDiaraySpringBoot.model.WeekJob;
import com.tutorial.ohDiaraySpringBoot.repository.MonthJobRepository;
import com.tutorial.ohDiaraySpringBoot.repository.WeekJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeekJobService {
    @Autowired
    private MonthJobRepository monthJobRepository;
    @Autowired
    private WeekJobRepository weekJobRepository;

    public JobDTO save(JobDTO jobDTO) {
        if (jobDTO.getJobType() != 3) return null;

        MonthJob parentJob = monthJobRepository.findById(jobDTO.getParentId()).get(); // TODO: Need exception handling
        WeekJob weekJob = new WeekJob(jobDTO.getTitle(), jobDTO.getContent(), jobDTO.getFromTime(), jobDTO.getToTime(), parentJob);

        weekJobRepository.save(weekJob);

        return jobDTO;
    }

    public JobDTO get(Long id) {
        JobDTO dto = new JobDTO();
        dto.setJobType(3);

        WeekJob weekJob = weekJobRepository.findById(id).get();

        dto.setId(weekJob.getId());
        dto.setTitle(weekJob.getTitle());
        dto.setContent(weekJob.getContent());
        dto.setFromTime(weekJob.getFromTime());
        dto.setToTime(weekJob.getToTime());
        dto.setParentId(weekJob.getMonthJob().getId());

        return dto;
    }

    public void delete(Long id) {
        weekJobRepository.deleteById(id);
    }
}
