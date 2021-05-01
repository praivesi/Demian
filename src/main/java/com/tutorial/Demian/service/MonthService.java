package com.tutorial.Demian.service;

import java.util.*;

import com.tutorial.Demian.controller.MonthController;
import com.tutorial.Demian.dto.DesireDTO;
import com.tutorial.Demian.dto.JobDTO;
import com.tutorial.Demian.dto.MonthDTO;
import com.tutorial.Demian.model.Desire;
import com.tutorial.Demian.model.Month;
import com.tutorial.Demian.repository.DesireRepository;
import com.tutorial.Demian.repository.MonthRepository;
import com.tutorial.Demian.service.Utility.JobFilter;
import com.tutorial.Demian.service.Utility.TimeHeaderCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonthService {
    @Autowired
    private DesireRepository desireRepository;
    @Autowired
    private MonthRepository monthRepository;

    public MonthController.Response getMonthPageResp(Long userId, List<Desire> desires, int startYear, int startMonth) {
        MonthController.Response response = new MonthController.Response();

        Calendar startCal = new GregorianCalendar();
        if (startYear == MonthController.UNDEFINED_YEAR || startMonth == MonthController.UNDEFINED_MONTH) {
            startYear = startCal.get(Calendar.YEAR);
            startMonth = (startCal.get(Calendar.MONTH) - 2) % 12;
        }

        startCal.set(Calendar.YEAR, startYear);
        startCal.set(Calendar.MONTH, startMonth);
        Date startDate = startCal.getTime();

        for (Desire desire : desires) {
            MonthController.DesireWithMonth desireWithMonth = new MonthController.DesireWithMonth();

            desireWithMonth.setDesire(DesireDTO.of(desire));

            List<MonthDTO> filteredMonths = JobFilter.monthFilter(desire.getMonths(), startDate, 6);
            desireWithMonth.setMonths(filteredMonths);

            response.getDesireWithMonths().add(desireWithMonth);
        }

        List<String> timeHeaders = TimeHeaderCalculator.getMonthTimeHeaders(startCal, 6);
        response.setTimeHeaders(timeHeaders);

        response.setStartDate(startDate);

        return response;
    }

    public Month getEntity(Long jobId) {
        return monthRepository.findById(jobId).orElse(null);
    }

    public Month save(Month month) { return monthRepository.save(month); }

    public JobDTO save(JobDTO jobDTO) {
        if (jobDTO.getJobType() != 2) return null;

        Optional<Desire> maybeParentJob = desireRepository.findById(jobDTO.getParentId());
        if (maybeParentJob.isPresent()) {
            Month newMonth = new Month(jobDTO.getTitle(), jobDTO.getContent(), jobDTO.getFromTime(), jobDTO.getToTime(), maybeParentJob.get());
            Month entity = monthRepository.save(newMonth);
            jobDTO.setId(entity.getId());
        } else {
            jobDTO = new JobDTO();
        }

        return jobDTO;
    }

    public JobDTO update(JobDTO dto, Long id) {
        Optional<Month> maybeEntity = monthRepository.findById(id);

        if (maybeEntity.isPresent()) {
            Month entity = maybeEntity.get();

            entity.setTitle(dto.getTitle());
            entity.setContent(dto.getContent());
            entity.setFromTime(dto.getFromTime());
            entity.setToTime(dto.getToTime());

            monthRepository.save(entity);
            dto.setId(id);
        } else {
            dto = new JobDTO();
        }

        return dto;
    }

    public JobDTO get(Long id) {
        JobDTO dto = new JobDTO();
        Optional<Month> maybeMonthJob = monthRepository.findById(id);

        if (maybeMonthJob.isPresent()) {
            Month entity = maybeMonthJob.get();

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
            monthRepository.deleteById(id);
        } catch (Exception e) {
            id = -1l;
        }

        return id;
    }
}
