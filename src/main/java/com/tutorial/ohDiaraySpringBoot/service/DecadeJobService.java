package com.tutorial.ohDiaraySpringBoot.service;

import com.tutorial.ohDiaraySpringBoot.dto.DecadeJobDTO;
import com.tutorial.ohDiaraySpringBoot.dto.DesireDTO;
import com.tutorial.ohDiaraySpringBoot.dto.JobDTO;
import com.tutorial.ohDiaraySpringBoot.model.DecadeJob;
import com.tutorial.ohDiaraySpringBoot.model.Desire;
import com.tutorial.ohDiaraySpringBoot.model.User;
import com.tutorial.ohDiaraySpringBoot.repository.DecadeJobRepository;
import com.tutorial.ohDiaraySpringBoot.repository.DesireRepository;
import com.tutorial.ohDiaraySpringBoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DecadeJobService {
    @Autowired
    private DesireRepository desireRepository;

    @Autowired
    private DecadeJobRepository decadeJobRepository;

    @Autowired
    private UserRepository userRepository;

    public DecadeJob savePrev(String username, DecadeJob decadeJob) {
        User user = userRepository.findByUsername(username);

        return decadeJobRepository.save(decadeJob);
    }

    public JobDTO save(JobDTO jobDTO) {
        if (jobDTO.getJobType() != 0) return null;

        Optional<Desire> maybeDesire = desireRepository.findById(jobDTO.getParentId());
        if (maybeDesire.isPresent()) {
            DecadeJob newDecadeJob = new DecadeJob(jobDTO.getTitle(), jobDTO.getContent(),
                    jobDTO.getFromTime(), jobDTO.getToTime(), maybeDesire.get());
            DecadeJob entity = decadeJobRepository.save(newDecadeJob);
            jobDTO.setId(entity.getId());
        } else {
            jobDTO = new JobDTO();
        }

        return jobDTO;
    }

    public JobDTO update(JobDTO dto, Long id) {
        Optional<DecadeJob> maybeEntity = decadeJobRepository.findById(id);

        if (maybeEntity.isPresent()) {
            DecadeJob entity = maybeEntity.get();

            entity.setTitle(dto.getTitle());
            entity.setContent(dto.getContent());
            entity.setFromTime(dto.getFromTime());
            entity.setToTime(dto.getToTime());

            decadeJobRepository.save(entity);
            dto.setId(id);
        } else {
            dto = new JobDTO();
        }

        return dto;
    }

    public JobDTO get(Long id) {
        JobDTO dto = new JobDTO();
        Optional<DecadeJob> maybeDecadeJob = decadeJobRepository.findById(id);

        if (maybeDecadeJob.isPresent()) {
            DecadeJob entity = maybeDecadeJob.get();

            dto.setJobType(0);
            dto.setId(entity.getId());
            dto.setTitle(entity.getTitle());
            dto.setContent(entity.getContent());
            dto.setFromTime(entity.getFromTime());
            dto.setToTime(entity.getToTime());
            dto.setParentId(entity.getDesire().getId());
        }

        return dto;
    }

    public Long delete(Long id) {
        try {
            decadeJobRepository.deleteById(id);
        } catch (Exception e) {
            id = -1l;
        }

        return id;
    }
}
