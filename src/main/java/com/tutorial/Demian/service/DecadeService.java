package com.tutorial.Demian.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tutorial.Demian.controller.DecadeController;
import com.tutorial.Demian.dto.DecadeDTO;
import com.tutorial.Demian.dto.DesireDTO;
import com.tutorial.Demian.dto.JobDTO;
import com.tutorial.Demian.model.DecadeGrowth;
import com.tutorial.Demian.model.DesireGrowth;
import com.tutorial.Demian.repository.DecadeRepository;
import com.tutorial.Demian.repository.DesireRepository;
import com.tutorial.Demian.service.Utility.JobFilter;
import com.tutorial.Demian.service.Utility.TimeHeaderCalculator;

@Service
public class DecadeService {
    @Autowired
    private DesireRepository desireRepository;
    @Autowired
    private DecadeRepository decadeRepository;

    public DecadeController.Response getDecadePageResp(Long userId, List<DesireGrowth> desires, int startDecade) {
        DecadeController.Response response = new DecadeController.Response();

        Calendar startCal = this.getStartCalendar(startDecade);

        for (DesireGrowth desire : desires) {
            DecadeController.DesireWithDecade desireWithDecade = this.getDesireWithDecade(desire, startCal.getTime());

            response.getDesireWithDecades().add(desireWithDecade);
        }

        List<String> timeHeaders = TimeHeaderCalculator.getDecadeTimeHeaders(startCal, 5);

        response.setTimeHeaders(timeHeaders);
        response.setStartDate(startCal.getTime());

        return response;
    }

    private Calendar getStartCalendar(int startDecade) {
        Calendar startCal = new GregorianCalendar();
        if (startDecade == DecadeController.UNDEFINED_DECADE) {
            startDecade = startCal.get(Calendar.YEAR) - 20;
        }

        startCal.set(Calendar.YEAR, startDecade);

        return startCal;
    }

    private DecadeController.DesireWithDecade getDesireWithDecade(DesireGrowth desire, Date startDate) {
        DecadeController.DesireWithDecade desireWithDecade = new DecadeController.DesireWithDecade();

        desireWithDecade.setDesire(DesireDTO.of(desire));

        List<DecadeDTO> filteredDecades = JobFilter.decadeFilter(desire.getDecadeGrowths(), startDate, 5);
        desireWithDecade.setDecades(filteredDecades);

        return desireWithDecade;
    }

    public JobDTO save(JobDTO jobDTO) {
        if (jobDTO.getJobType() != 0) return null;

        Optional<DesireGrowth> maybeDesire = desireRepository.findById(jobDTO.getParentId());
        if (maybeDesire.isPresent()) {
            DecadeGrowth newDecadeGrowth = new DecadeGrowth(jobDTO.getTitle(), jobDTO.getContent(),
                    jobDTO.getFromTime(), jobDTO.getToTime(), maybeDesire.get());
            DecadeGrowth entity = decadeRepository.save(newDecadeGrowth);
            jobDTO.setId(entity.getId());
        } else {
            jobDTO = new JobDTO();
        }

        return jobDTO;
    }

    public JobDTO update(JobDTO dto, Long id) {
        Optional<DecadeGrowth> maybeEntity = decadeRepository.findById(id);

        if (!maybeEntity.isPresent()) {
            return new JobDTO();
        }

        DecadeGrowth updatedEntity = this.getUpdatedEntity(maybeEntity.get(), dto);
        decadeRepository.save(updatedEntity);

        dto.setId(id);

        return dto;
    }

    private DecadeGrowth getUpdatedEntity(DecadeGrowth entity, JobDTO dto){
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setFromTime(dto.getFromTime());
        entity.setToTime(dto.getToTime());

        return entity;
    }

    public JobDTO get(Long id) {
        Optional<DecadeGrowth> maybeDecadeJob = decadeRepository.findById(id);

        if (maybeDecadeJob.isPresent()) {
           return this.get(maybeDecadeJob.get());
        }

        return new JobDTO();
    }

    private JobDTO get(DecadeGrowth entity){
        JobDTO dto = new JobDTO();

        dto.setJobType(0);
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setFromTime(entity.getFromTime());
        dto.setToTime(entity.getToTime());
        dto.setParentId(entity.getDesire().getId());

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