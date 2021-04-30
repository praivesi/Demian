package com.tutorial.Demian.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

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

        Calendar startCal = new GregorianCalendar();
        if (startYear == YearController.UNDEFINED_YEAR) {
            startYear = startCal.get(Calendar.YEAR) - 2;
        }

        startCal.set(Calendar.YEAR, startYear);
        Date startDate = startCal.getTime();

        for (Desire desire : desires) {
            YearController.DesireWithYear desireWithYear = new YearController.DesireWithYear();

            desireWithYear.setDesire(DesireDTO.of(desire));

            List<YearDTO> filteredYears = JobFilter.yearFilter(desire.getYears(), startDate, 5);
            desireWithYear.setYears(filteredYears);

            response.getDesireWithYears().add(desireWithYear);
        }

        List<String> timeHeaders = TimeHeaderCalculator.getYearTimeHeaders(startCal, 5);
        response.setTimeHeaders(timeHeaders);

        response.setStartDate(startDate);

        return response;
    }

    public JobDTO save(JobDTO jobDTO) {
        if (jobDTO.getJobType() != 1) return null;

//        Optional<DecadeJob> maybeParentJob = decadeJobRepository.findById(jobDTO.getParentId());
        Optional<Desire> maybeParentJob = desireRepository.findById(jobDTO.getParentId());
        if (maybeParentJob.isPresent()) {
            Year newYear = new Year(jobDTO.getTitle(), jobDTO.getContent(), jobDTO.getFromTime(), jobDTO.getToTime(), maybeParentJob.get());
            Year entity = yearRepository.save(newYear);
            jobDTO.setId(entity.getId());
        } else {
            jobDTO = new JobDTO();
        }

        return jobDTO;
    }

    public JobDTO update(JobDTO dto, Long id) {
        Optional<Year> maybeEntity = yearRepository.findById(id);

        if (maybeEntity.isPresent()) {
            Year entity = maybeEntity.get();

            entity.setTitle(dto.getTitle());
            entity.setContent(dto.getContent());
            entity.setFromTime(dto.getFromTime());
            entity.setToTime(dto.getToTime());

            yearRepository.save(entity);
            dto.setId(id);
        } else {
            dto = new JobDTO();
        }

        return dto;
    }

    public JobDTO get(Long id) {
        JobDTO dto = new JobDTO();
        Optional<Year> maybeYearJob = yearRepository.findById(id);

        if (maybeYearJob.isPresent()) {
            Year entity = maybeYearJob.get();

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
            yearRepository.deleteById(id);
        } catch (Exception e) {
            id = -1l;
        }

        return id;
    }
}