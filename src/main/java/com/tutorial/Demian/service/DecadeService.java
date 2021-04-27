package com.tutorial.Demian.service;

import com.tutorial.Demian.controller.DecadeController;
import com.tutorial.Demian.dto.DecadeDTO;
import com.tutorial.Demian.dto.DesireDTO;
import com.tutorial.Demian.dto.JobDTO;
import com.tutorial.Demian.model.Decade;
import com.tutorial.Demian.model.Desire;
import com.tutorial.Demian.repository.DecadeRepository;
import com.tutorial.Demian.repository.DesireRepository;
import com.tutorial.Demian.service.Utility.JobFilter;
import com.tutorial.Demian.service.Utility.TimeHeaderCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DecadeService {
    @Autowired private DesireRepository desireRepository;
    @Autowired private DecadeRepository decadeRepository;

    public DecadeController.Response getDecadePageResp(Long userId, List<Desire> desires, int startDecade) {
        DecadeController.Response response = new DecadeController.Response();

        Calendar startCal = new GregorianCalendar();
        if (startDecade == DecadeController.UNDEFINED_DECADE) {
            startDecade = startCal.get(Calendar.YEAR) - 20;
        }

        startCal.set(Calendar.YEAR, startDecade);
        Date startDate = startCal.getTime();

        for (Desire desire : desires) {
            DecadeController.DesireWithDecade desireWithDecade = new DecadeController.DesireWithDecade();

            desireWithDecade.setDesire(DesireDTO.of(desire));

            List<DecadeDTO> filteredDecades = JobFilter.decadeFilter(desire.getDecades(), startDate, 5);
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
            Decade newDecade = new Decade(jobDTO.getTitle(), jobDTO.getContent(),
                    jobDTO.getFromTime(), jobDTO.getToTime(), maybeDesire.get());
            Decade entity = decadeRepository.save(newDecade);
            jobDTO.setId(entity.getId());
        } else {
            jobDTO = new JobDTO();
        }

        return jobDTO;
    }

    public JobDTO update(JobDTO dto, Long id) {
        Optional<Decade> maybeEntity = decadeRepository.findById(id);

        if (maybeEntity.isPresent()) {
            Decade entity = maybeEntity.get();

            entity.setTitle(dto.getTitle());
            entity.setContent(dto.getContent());
            entity.setFromTime(dto.getFromTime());
            entity.setToTime(dto.getToTime());

            decadeRepository.save(entity);
            dto.setId(id);
        } else {
            dto = new JobDTO();
        }

        return dto;
    }

    public JobDTO get(Long id) {
        JobDTO dto = new JobDTO();
        Optional<Decade> maybeDecadeJob = decadeRepository.findById(id);

        if (maybeDecadeJob.isPresent()) {
            Decade entity = maybeDecadeJob.get();

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
            decadeRepository.deleteById(id);
        } catch (Exception e) {
            id = -1l;
        }

        return id;
    }
}