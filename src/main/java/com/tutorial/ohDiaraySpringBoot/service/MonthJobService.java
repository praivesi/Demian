package com.tutorial.ohDiaraySpringBoot.service;

import com.tutorial.ohDiaraySpringBoot.dto.JobDTO;
import com.tutorial.ohDiaraySpringBoot.model.MonthJob;
import com.tutorial.ohDiaraySpringBoot.model.YearJob;
import com.tutorial.ohDiaraySpringBoot.repository.MonthJobRepository;
import com.tutorial.ohDiaraySpringBoot.repository.YearJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonthJobService {
    @Autowired
    private YearJobRepository yearJobRepository;
    @Autowired
    private MonthJobRepository monthJobRepository;

    public JobDTO save(JobDTO jobDTO) {
        if (jobDTO.getJobType() != 2) return null;

        YearJob parentJob = yearJobRepository.findById(jobDTO.getParentId()).get(); // TODO: Need exception handling
        MonthJob monthJob = new MonthJob(jobDTO.getTitle(), jobDTO.getContent(), jobDTO.getFromTime(), jobDTO.getToTime(), parentJob);

        monthJobRepository.save(monthJob);

        return jobDTO;
    }

    public JobDTO get(Long id) {
        JobDTO dto = new JobDTO();
        dto.setJobType(2);

        MonthJob monthJob = monthJobRepository.findById(id).get();

        dto.setId(monthJob.getId());
        dto.setTitle(monthJob.getTitle());
        dto.setContent(monthJob.getContent());
        dto.setFromTime(monthJob.getFromTime());
        dto.setToTime(monthJob.getToTime());
        dto.setParentId(monthJob.getYearJob().getId());

        return dto;
    }

    public Long delete(Long id) {
        boolean succeed = false;

        try {
            monthJobRepository.deleteById(id);
            succeed = true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return succeed ? id : -1;
    }
}
