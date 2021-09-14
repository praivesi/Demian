package com.tutorial.Demian.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tutorial.Demian.controller.YearGrowthController;
import com.tutorial.Demian.dto.*;
import com.tutorial.Demian.model.Desire;
import com.tutorial.Demian.model.YearGrowth;
import com.tutorial.Demian.repository.DesireRepository;
import com.tutorial.Demian.repository.YearRepository;
import com.tutorial.Demian.service.Utility.JobFilter;
import com.tutorial.Demian.service.Utility.TimeHeaderCalculator;

@Service
public class YearService {
    @Autowired
    private DesireRepository desireRepository;
    @Autowired
    private DesireService desireService;
    @Autowired
    private YearRepository yearRepository;

    public YearGrowthController.Response getYearPageResp(Long userId, List<Desire> desires, int startYear) {
        YearGrowthController.Response response = new YearGrowthController.Response();
        Calendar startCal = this.getStartCal(startYear);

        response.setDesireWithYears(this.getDesireWithYears(startCal.getTime(), desires));
        response.setTimeHeaders(TimeHeaderCalculator.getYearTimeHeaders(startCal, 5));
        response.setStartDate(startCal.getTime());

        return response;
    }

    private Calendar getStartCal(int startYear) {
        Calendar startCal = new GregorianCalendar();

        if (startYear == YearGrowthController.UNDEFINED_YEAR) {
            startYear = startCal.get(Calendar.YEAR) - 2;
        }

        startCal.set(Calendar.YEAR, startYear);

        return startCal;
    }

    private List<YearGrowthController.DesireWithYear> getDesireWithYears(Date startDate, List<Desire> desires) {
        List<YearGrowthController.DesireWithYear> desireWithYears = new ArrayList<>();

        for (Desire desire : desires) {
            YearGrowthController.DesireWithYear desireWithYear = this.getDesireWithYear(desire, startDate);

            desireWithYears.add(desireWithYear);
        }

        return desireWithYears;
    }

    private YearGrowthController.DesireWithYear getDesireWithYear(Desire desire, Date startDate) {
        YearGrowthController.DesireWithYear desireWithYear = new YearGrowthController.DesireWithYear();

        desireWithYear.setDesire(DesireDTO.of(desire));

        List<YearGrowthDTO> filteredYears = JobFilter.yearFilter(desire.getYearGrowths(), startDate, 5);
        desireWithYear.setYears(filteredYears);

        return desireWithYear;
    }

    public YearGrowth findYear(long jobId) {
        return yearRepository.findById(jobId).orElse(null);
    }

    public JobDTO save(JobDTO jobDTO) {
        if (jobDTO.getJobType() != 1) return null;

        Optional<Desire> maybeParentJob = desireRepository.findById(jobDTO.getParentId());
        if (!maybeParentJob.isPresent()) {
            return new JobDTO();
        }

        YearGrowth newYearGrowth = new YearGrowth(jobDTO.getTitle(), jobDTO.getContent(), jobDTO.getYearNumber(), maybeParentJob.get());
        YearGrowth entity = yearRepository.save(newYearGrowth);
        jobDTO.setId(entity.getId());

        return jobDTO;
    }

    public YearGrowth save(YearGrowth yearGrowth) {
        return yearRepository.save(yearGrowth);
    }

    public JobDTO update(JobDTO dto, Long id) {
        Optional<YearGrowth> maybeEntity = yearRepository.findById(id);

        if (!maybeEntity.isPresent()) {
            return new JobDTO();
        }

        this.updateInternal(maybeEntity.get(), dto);
        dto.setId(id);

        return dto;
    }

    private YearGrowth updateInternal(YearGrowth entity, JobDTO dto) {
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setYearNumber(dto.getYearNumber());

        return yearRepository.save(entity);
    }

    public JobDTO get(Long id) {
        Optional<YearGrowth> maybeYearJob = yearRepository.findById(id);

        if (!maybeYearJob.isPresent()) {
            return new JobDTO();
        }

        return this.getInternal(maybeYearJob.get());
    }

    private JobDTO getInternal(YearGrowth entity) {
        JobDTO dto = new JobDTO();

        dto.setJobType(1);
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setYearNumber(entity.getYearNumber());
        dto.setParentId(entity.getDesire().getId());

        return dto;
    }

    public Long delete(Long id) {
        try {
            yearRepository.deleteById(id);
        } catch (Exception e) {
            id = -1l;
        }

        return id;
    }
}