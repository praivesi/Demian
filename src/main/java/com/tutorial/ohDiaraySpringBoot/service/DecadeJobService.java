package com.tutorial.ohDiaraySpringBoot.service;

import com.tutorial.ohDiaraySpringBoot.dto.JobDTO;
import com.tutorial.ohDiaraySpringBoot.model.DecadeJob;
import com.tutorial.ohDiaraySpringBoot.model.Desire;
import com.tutorial.ohDiaraySpringBoot.model.User;
import com.tutorial.ohDiaraySpringBoot.repository.DecadeJobRepository;
import com.tutorial.ohDiaraySpringBoot.repository.DesireRepository;
import com.tutorial.ohDiaraySpringBoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        Desire parentDesire = desireRepository.findById(jobDTO.getParentId()).get(); // TODO: Need exception handling
        DecadeJob decade = new DecadeJob(jobDTO.getTitle(), jobDTO.getContent(),
                jobDTO.getFromTime(), jobDTO.getToTime(), parentDesire);

        decadeJobRepository.save(decade);

        return jobDTO;
    }

    public JobDTO get(Long id) {
        JobDTO dto = new JobDTO();
        dto.setJobType(0);

        DecadeJob decadeJob = decadeJobRepository.findById(id).get();

        dto.setId(decadeJob.getId());
        dto.setTitle(decadeJob.getTitle());
        dto.setContent(decadeJob.getContent());
        dto.setFromTime(decadeJob.getFromTime());
        dto.setToTime(decadeJob.getToTime());
        dto.setParentId(decadeJob.getDesire().getId());

        return dto;
    }

    public void delete(Long id) {
        decadeJobRepository.deleteById(id);
    }
}
