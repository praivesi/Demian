package com.tutorial.Demian.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tutorial.Demian.controller.YearController;
import com.tutorial.Demian.dto.*;
import com.tutorial.Demian.model.Desire;
import com.tutorial.Demian.model.Year;
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

    public YearController.Response getYearPageResp(Long userId, List<Desire> desires, int startYear) {
        YearController.Response response = new YearController.Response();
        Calendar startCal = this.getStartCal(startYear);

        response.setDesireWithYears(this.getDesireWithYears(startCal.getTime(), desires));
        response.setTimeHeaders(TimeHeaderCalculator.getYearTimeHeaders(startCal, 5));
        response.setStartDate(startCal.getTime());

        return response;
    }

    private Calendar getStartCal(int startYear) {
        Calendar startCal = new GregorianCalendar();

        if (startYear == YearController.UNDEFINED_YEAR) {
            startYear = startCal.get(Calendar.YEAR) - 2;
        }

        startCal.set(Calendar.YEAR, startYear);

        return startCal;
    }

    private List<YearController.DesireWithYear> getDesireWithYears(Date startDate, List<Desire> desires) {
        List<YearController.DesireWithYear> desireWithYears = new ArrayList<>();

        for (Desire desire : desires) {
            YearController.DesireWithYear desireWithYear = this.getDesireWithYear(desire, startDate);

            desireWithYears.add(desireWithYear);
        }

        return desireWithYears;
    }

    private YearController.DesireWithYear getDesireWithYear(Desire desire, Date startDate) {
        YearController.DesireWithYear desireWithYear = new YearController.DesireWithYear();

        desireWithYear.setDesire(DesireDTO.of(desire));

        List<YearDTO> filteredYears = JobFilter.yearFilter(desire.getYears(), startDate, 5);
        desireWithYear.setYears(filteredYears);

        return desireWithYear;
    }

    public Year findYear(long jobId) {
        return yearRepository.findById(jobId).orElse(null);
    }

    public JobDTO save(JobDTO jobDTO) {
        if (jobDTO.getJobType() != 1) return null;

        Optional<Desire> maybeParentJob = desireRepository.findById(jobDTO.getParentId());
        if (!maybeParentJob.isPresent()) {
            return new JobDTO();
        }

        Year newYear = new Year(jobDTO.getTitle(), jobDTO.getContent(), jobDTO.getFromTime(), jobDTO.getToTime(), maybeParentJob.get());
        Year entity = yearRepository.save(newYear);
        jobDTO.setId(entity.getId());

        return jobDTO;
    }

    public Year save(Year year) {
        return yearRepository.save(year);
    }

    public JobDTO update(JobDTO dto, Long id) {
        Optional<Year> maybeEntity = yearRepository.findById(id);

        if (!maybeEntity.isPresent()) {
            return new JobDTO();
        }

        this.updateInternal(maybeEntity.get(), dto);
        dto.setId(id);

        return dto;
    }

    private Year updateInternal(Year entity, JobDTO dto) {
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setFromTime(dto.getFromTime());
        entity.setToTime(dto.getToTime());

        return yearRepository.save(entity);
    }

    public JobDTO get(Long id) {
        Optional<Year> maybeYearJob = yearRepository.findById(id);

        if (!maybeYearJob.isPresent()) {
            return new JobDTO();
        }

        return this.getInternal(maybeYearJob.get());
    }

    private JobDTO getInternal(Year entity) {
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
            yearRepository.deleteById(id);
        } catch (Exception e) {
            id = -1l;
        }

        return id;
    }
}