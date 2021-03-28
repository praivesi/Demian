package com.tutorial.ohDiaraySpringBoot.service;

import com.tutorial.ohDiaraySpringBoot.dto.JobDTO;
import com.tutorial.ohDiaraySpringBoot.model.DecadeJob;
import com.tutorial.ohDiaraySpringBoot.model.YearJob;
import com.tutorial.ohDiaraySpringBoot.repository.DecadeJobRepository;
import com.tutorial.ohDiaraySpringBoot.repository.YearJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class YearJobService {
    @Autowired
    private DecadeJobRepository decadeJobRepository;
    @Autowired
    private YearJobRepository yearJobRepository;

    public JobDTO save(JobDTO jobDTO) {
        if (jobDTO.getJobType() != 1) return null;

        boolean succeed = false;

        Optional<DecadeJob> maybeParentJob = decadeJobRepository.findById(jobDTO.getParentId());
        if (maybeParentJob.isPresent()) {
            YearJob newYearJob = new YearJob(jobDTO.getTitle(), jobDTO.getContent(), jobDTO.getFromTime(), jobDTO.getToTime(), maybeParentJob.get());
            YearJob entity = yearJobRepository.save(newYearJob);
            jobDTO.setId(entity.getId());

            succeed = true;
        }

        return succeed ? jobDTO : new JobDTO();
    }

    public JobDTO get(Long id) {
        JobDTO dto = new JobDTO();
        Optional<YearJob> maybeYearJob = yearJobRepository.findById(id);

        if(maybeYearJob.isPresent()){
            YearJob entity = maybeYearJob.get();

            dto.setJobType(1);
            dto.setId(entity.getId());
            dto.setTitle(entity.getTitle());
            dto.setContent(entity.getContent());
            dto.setFromTime(entity.getFromTime());
            dto.setToTime(entity.getToTime());
            dto.setParentId(entity.getDecadeJob().getId());
        }

        return dto;
    }

    public void delete(Long id) {
        boolean succeed = false;

        try {
            yearJobRepository.deleteById(id);

            succeed = true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        // return succeed;
    }
}