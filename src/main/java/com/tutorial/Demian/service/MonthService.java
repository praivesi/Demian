package com.tutorial.Demian.service;

import java.util.*;

import com.tutorial.Demian.controller.MonthGrowthController;
import com.tutorial.Demian.dto.DesireDTO;
import com.tutorial.Demian.dto.JobDTO;
import com.tutorial.Demian.dto.MonthGrowthDTO;
import com.tutorial.Demian.model.Desire;
import com.tutorial.Demian.model.MonthGrowth;
import com.tutorial.Demian.repository.DesireRepository;
import com.tutorial.Demian.repository.MonthGrowthRepository;
import com.tutorial.Demian.service.Utility.JobFilter;
import com.tutorial.Demian.service.Utility.TimeHeaderCalculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonthService {
    @Autowired
    private DesireRepository desireRepository;
    @Autowired
    private MonthGrowthRepository monthGrowthRepository;

    public MonthGrowthController.Response getMonthPageResp(Long userId, List<Desire> desires, int startYear, int startMonth) {
        MonthGrowthController.Response response = new MonthGrowthController.Response();

        Calendar startCal = this.getStartCal(new GregorianCalendar(), startYear, startMonth);
        Date startDate = startCal.getTime();

        List<MonthGrowthController.DesireWithMonth> desireWithMonths = this.getDesireWithMonths(startCal.getTime(), desires);
        response.setDesireWithMonths(desireWithMonths);

        List<String> timeHeaders = TimeHeaderCalculator.getMonthTimeHeaders(startCal, 6);
        response.setTimeHeaders(timeHeaders);

        response.setStartDate(startDate);

        return response;
    }

    private Calendar getStartCal(Calendar startCal, int startYear, int startMonth) {
        if (startYear == MonthGrowthController.UNDEFINED_YEAR || startMonth == MonthGrowthController.UNDEFINED_MONTH) {
            startYear = startCal.get(Calendar.YEAR);
            startMonth = (startCal.get(Calendar.MONTH) - 2) % 12;
        }

        startCal.set(Calendar.YEAR, startYear);
        startCal.set(Calendar.MONTH, startMonth);

        return startCal;
    }

    private List<MonthGrowthController.DesireWithMonth> getDesireWithMonths(Date startDate, List<Desire> desires) {
        List<MonthGrowthController.DesireWithMonth> desireWithMonths = new ArrayList<>();

        for (Desire desire : desires) {
            MonthGrowthController.DesireWithMonth desireWithMonth = new MonthGrowthController.DesireWithMonth();

            desireWithMonth.setDesire(DesireDTO.of(desire));

            List<MonthGrowthDTO> filteredMonths = JobFilter.monthFilter(desire.getMonthGrowths(), startDate, 6);
            desireWithMonth.setMonths(filteredMonths);

            desireWithMonths.add(desireWithMonth);
        }

        return desireWithMonths;
    }

    public MonthGrowth getEntity(Long jobId) {
        return monthGrowthRepository.findById(jobId).orElse(null);
    }

    public MonthGrowth save(MonthGrowth monthGrowth) {
        return monthGrowthRepository.save(monthGrowth);
    }

    public JobDTO save(JobDTO jobDTO) {
        if (jobDTO.getJobType() != 2) return null;

        Optional<Desire> maybeParentJob = desireRepository.findById(jobDTO.getParentId());
        if (!maybeParentJob.isPresent()) {
            return new JobDTO();
        }

        MonthGrowth newMonthGrowth = new MonthGrowth(jobDTO.getTitle(), jobDTO.getContent(), jobDTO.getFromTime(), jobDTO.getToTime(), maybeParentJob.get());
        MonthGrowth entity = monthGrowthRepository.save(newMonthGrowth);
        jobDTO.setId(entity.getId());

        return jobDTO;
    }

    public JobDTO update(JobDTO dto, Long id) {
        Optional<MonthGrowth> maybeEntity = monthGrowthRepository.findById(id);

        if (!maybeEntity.isPresent()) {
            return new JobDTO();
        }

        this.updateInternal(maybeEntity.get(), dto);

        dto.setId(id);

        return dto;
    }

    private MonthGrowth updateInternal(MonthGrowth entity, JobDTO dto) {
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setFromTime(dto.getFromTime());
        entity.setToTime(dto.getToTime());

        return monthGrowthRepository.save(entity);
    }

    public JobDTO get(Long id) {

        Optional<MonthGrowth> maybeMonthJob = monthGrowthRepository.findById(id);

        if (!maybeMonthJob.isPresent()) {
            return new JobDTO();
        }

        MonthGrowth entity = maybeMonthJob.get();

        JobDTO dto = new JobDTO();
        dto.setJobType(1);
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
            monthGrowthRepository.deleteById(id);
        } catch (Exception e) {
            id = -1l;
        }

        return id;
    }
}
