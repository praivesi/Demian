package com.tutorial.Demian.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tutorial.Demian.controller.DecadeGrowthController;
import com.tutorial.Demian.dto.DecadeGrowthDTO;
import com.tutorial.Demian.dto.DesireDTO;
import com.tutorial.Demian.dto.JobDTO;
import com.tutorial.Demian.model.DecadeGrowth;
import com.tutorial.Demian.model.Desire;
import com.tutorial.Demian.repository.DecadeGrowthRepository;
import com.tutorial.Demian.repository.DesireRepository;
import com.tutorial.Demian.service.Utility.JobFilter;
import com.tutorial.Demian.service.Utility.TimeHeaderCalculator;

@Service
public class DecadeService {
    @Autowired
    private DesireRepository desireRepository;
    @Autowired
    private DecadeGrowthRepository decadeGrowthRepository;

    public DecadeGrowthController.Response getDecadePageResp(Long userId, List<Desire> desires, int startDecade) {
        DecadeGrowthController.Response response = new DecadeGrowthController.Response();

        Calendar startCal = this.getStartCalendar(startDecade);

        for (Desire desire : desires) {
            DecadeGrowthController.DesireWithDecade desireWithDecade = this.getDesireWithDecade(desire, startCal.getTime());

            response.getDesireWithDecades().add(desireWithDecade);
        }

        List<String> timeHeaders = TimeHeaderCalculator.getDecadeTimeHeaders(startCal, 5);

        response.setTimeHeaders(timeHeaders);
        response.setStartDate(startCal.getTime());

        return response;
    }

    private Calendar getStartCalendar(int startDecade) {
        Calendar startCal = new GregorianCalendar();
        if (startDecade == DecadeGrowthController.UNDEFINED_DECADE) {
            startDecade = startCal.get(Calendar.YEAR) - 20;
        }

        startCal.set(Calendar.YEAR, startDecade);

        return startCal;
    }

    private DecadeGrowthController.DesireWithDecade getDesireWithDecade(Desire desire, Date startDate) {
        DecadeGrowthController.DesireWithDecade desireWithDecade = new DecadeGrowthController.DesireWithDecade();

        desireWithDecade.setDesire(DesireDTO.of(desire));

        List<DecadeGrowthDTO> filteredDecades = JobFilter.decadeFilter(desire.getDecadeGrowths(), startDate, 5);
        desireWithDecade.setDecades(filteredDecades);

        return desireWithDecade;
    }

    public JobDTO save(JobDTO jobDTO) {
        if (jobDTO.getJobType() != 0) return null;

        Optional<Desire> maybeDesire = desireRepository.findById(jobDTO.getParentId());
        if (maybeDesire.isPresent()) {
            DecadeGrowth newDecadeGrowth = new DecadeGrowth(jobDTO.getTitle(), jobDTO.getContent(),
                    jobDTO.getDecadeNumber(), maybeDesire.get());
            DecadeGrowth entity = decadeGrowthRepository.save(newDecadeGrowth);
            jobDTO.setId(entity.getId());
        } else {
            jobDTO = new JobDTO();
        }

        return jobDTO;
    }

    public JobDTO update(JobDTO dto, Long id) {
        Optional<DecadeGrowth> maybeEntity = decadeGrowthRepository.findById(id);

        if (!maybeEntity.isPresent()) {
            return new JobDTO();
        }

        DecadeGrowth updatedEntity = this.getUpdatedEntity(maybeEntity.get(), dto);
        decadeGrowthRepository.save(updatedEntity);

        dto.setId(id);

        return dto;
    }

    private DecadeGrowth getUpdatedEntity(DecadeGrowth entity, JobDTO dto){
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setDecadeNumber(dto.getDecadeNumber());
        return entity;
    }

    public JobDTO get(Long id) {
        Optional<DecadeGrowth> maybeDecadeJob = decadeGrowthRepository.findById(id);

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
        dto.setDecadeNumber(entity.getDecadeNumber());
        dto.setParentId(entity.getDesire().getId());

        return dto;
    }

    public Long delete(Long id) {
        try {
            decadeGrowthRepository.deleteById(id);
        } catch (Exception e) {
            id = -1l;
        }

        return id;
    }
}