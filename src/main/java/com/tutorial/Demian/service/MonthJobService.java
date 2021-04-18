package com.tutorial.Demian.service;

import com.tutorial.Demian.controller.MonthJobController;
import com.tutorial.Demian.controller.YearJobController;
import com.tutorial.Demian.dto.*;
import com.tutorial.Demian.model.Desire;
import com.tutorial.Demian.model.MonthJob;
import com.tutorial.Demian.repository.DesireRepository;
import com.tutorial.Demian.repository.MonthJobRepository;
import com.tutorial.Demian.service.Utility.JobFilter;
import com.tutorial.Demian.service.Utility.TimeHeaderCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MonthJobService {
    @Autowired
    private DesireRepository desireRepository;
    @Autowired
    private MonthJobRepository monthJobRepository;

    public MonthJobController.Response getMonthPageResp(Long userId, List<Desire> desires, int startYear, int startMonth) {
        MonthJobController.Response response = new MonthJobController.Response();

        Calendar startCal = new GregorianCalendar();
        if (startYear == MonthJobController.UNDEFINED_YEAR || startMonth == MonthJobController.UNDEFINED_MONTH) {
            startYear = startCal.get(Calendar.YEAR);
            startMonth = (startCal.get(Calendar.MONTH) - 2) % 12;
        }

        startCal.set(Calendar.YEAR, startYear);
        startCal.set(Calendar.MONTH, startMonth);
        Date startDate = startCal.getTime();

        for (Desire desire : desires) {
            MonthJobController.DesireWithMonth desireWithMonth = new MonthJobController.DesireWithMonth();

            desireWithMonth.setDesire(DesireDTO.of(desire));

            List<MonthJobDTO> filteredMonths = JobFilter.monthFilter(desire.getMonths(), startDate, 6);
            desireWithMonth.setMonths(filteredMonths);

            response.getDesireWithMonths().add(desireWithMonth);
        }

        List<String> timeHeaders = TimeHeaderCalculator.getMonthTimeHeaders(startCal, 6);
        response.setTimeHeaders(timeHeaders);

        response.setStartDate(startDate);

        return response;
    }

    public JobDTO save(JobDTO jobDTO) {
        if (jobDTO.getJobType() != 2) return null;

        Optional<Desire> maybeParentJob = desireRepository.findById(jobDTO.getParentId());
        if (maybeParentJob.isPresent()) {
            MonthJob newMonthJob = new MonthJob(jobDTO.getTitle(), jobDTO.getContent(), jobDTO.getFromTime(), jobDTO.getToTime(), maybeParentJob.get());
            MonthJob entity = monthJobRepository.save(newMonthJob);
            jobDTO.setId(entity.getId());
        } else {
            jobDTO = new JobDTO();
        }

        return jobDTO;
    }

    public JobDTO update(JobDTO dto, Long id) {
        Optional<MonthJob> maybeEntity = monthJobRepository.findById(id);

        if (maybeEntity.isPresent()) {
            MonthJob entity = maybeEntity.get();

            entity.setTitle(dto.getTitle());
            entity.setContent(dto.getContent());
            entity.setFromTime(dto.getFromTime());
            entity.setToTime(dto.getToTime());

            monthJobRepository.save(entity);
            dto.setId(id);
        } else {
            dto = new JobDTO();
        }

        return dto;
    }

    public JobDTO get(Long id) {
        JobDTO dto = new JobDTO();
        Optional<MonthJob> maybeMonthJob = monthJobRepository.findById(id);

        if (maybeMonthJob.isPresent()) {
            MonthJob entity = maybeMonthJob.get();

            dto.setJobType(1);
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
            monthJobRepository.deleteById(id);
        } catch (Exception e) {
            id = -1l;
        }

        return id;
    }
}
