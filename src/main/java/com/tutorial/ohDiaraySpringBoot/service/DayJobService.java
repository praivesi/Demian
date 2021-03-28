package com.tutorial.ohDiaraySpringBoot.service;

import com.tutorial.ohDiaraySpringBoot.dto.JobDTO;
import com.tutorial.ohDiaraySpringBoot.model.DayJob;
import com.tutorial.ohDiaraySpringBoot.model.WeekJob;
import com.tutorial.ohDiaraySpringBoot.repository.DayJobRepository;
import com.tutorial.ohDiaraySpringBoot.repository.WeekJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DayJobService {
    @Autowired
    private WeekJobRepository weekJobRepository;
    @Autowired
    private DayJobRepository dayJobRepository;

    public JobDTO save(JobDTO jobDTO) {
        if (jobDTO.getJobType() != 4) return null;

        WeekJob parentJob = weekJobRepository.findById(jobDTO.getParentId()).get(); // TODO: Need exception handling
        DayJob dayJob = new DayJob(jobDTO.getTitle(), jobDTO.getContent(), jobDTO.getFromTime(), jobDTO.getToTime(), parentJob);

        dayJobRepository.save(dayJob);

        return jobDTO;
    }

    public JobDTO get(Long id) {
        JobDTO dto = new JobDTO();
        dto.setJobType(4);

        DayJob dayJob = dayJobRepository.findById(id).get();

        dto.setId(dayJob.getId());
        dto.setTitle(dayJob.getTitle());
        dto.setContent(dayJob.getContent());
        dto.setFromTime(dayJob.getFromTime());
        dto.setToTime(dayJob.getToTime());
        dto.setParentId(dayJob.getWeekJob().getId());

        return dto;
    }

    public Long delete(Long id) {
        boolean succeed = false;

        try {
            dayJobRepository.deleteById(id);
            succeed = true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return succeed ? id : -1;
    }
}
