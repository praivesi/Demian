package com.tutorial.Demian.service;

import com.tutorial.Demian.controller.DecadeJobController;
import com.tutorial.Demian.dto.DecadeJobDTO;
import com.tutorial.Demian.dto.DesireDTO;
import com.tutorial.Demian.dto.JobDTO;
import com.tutorial.Demian.model.DecadeJob;
import com.tutorial.Demian.model.Desire;
import com.tutorial.Demian.repository.DecadeJobRepository;
import com.tutorial.Demian.repository.DesireRepository;
import com.tutorial.Demian.repository.UserRepository;
import com.tutorial.Demian.service.Utility.JobFilter;
import com.tutorial.Demian.service.Utility.TimeHeaderCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DecadeJobService {
    @Autowired
    private DesireRepository desireRepository;

    @Autowired
    private DecadeJobRepository decadeJobRepository;

    @Autowired
    private UserRepository userRepository;

    public DecadeJobController.Response getDecadePageResp(Long userId, List<Desire> desires, int startDecade) {
        DecadeJobController.Response response = new DecadeJobController.Response();

        Calendar startCal = new GregorianCalendar();
        if (startDecade == DecadeJobController.UNDEFINED_DECADE) {
            startDecade = startCal.get(Calendar.YEAR) - 20;
        }

        startCal.set(Calendar.YEAR, startDecade);
        Date startDate = startCal.getTime();

        for (Desire desire : desires) {
            DecadeJobController.DesireWithDecade desireWithDecade = new DecadeJobController.DesireWithDecade();

            desireWithDecade.setDesire(DesireDTO.of(desire));

            List<DecadeJobDTO> filteredDecades = JobFilter.decadeFilter(desire.getDecades(), startDate, 5);
            desireWithDecade.setDecades(filteredDecades);

            response.getDesireWithDecades().add(desireWithDecade);
        }

        List<String> timeHeaders = TimeHeaderCalculator.getDecadeTimeHeaders(startCal, 5);
        response.setTimeHeaders(timeHeaders);

        response.setStartDate(startDate);

        return response;
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
