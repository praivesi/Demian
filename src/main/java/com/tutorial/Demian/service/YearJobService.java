package com.tutorial.Demian.service;

import com.tutorial.Demian.controller.DecadeJobController;
import com.tutorial.Demian.controller.YearJobController;
import com.tutorial.Demian.dto.*;
import com.tutorial.Demian.model.Desire;
import com.tutorial.Demian.model.YearJob;
import com.tutorial.Demian.repository.DesireRepository;
import com.tutorial.Demian.repository.YearJobRepository;
import com.tutorial.Demian.service.Utility.JobFilter;
import com.tutorial.Demian.service.Utility.TimeHeaderCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class YearJobService {
    @Autowired
    private DesireRepository desireRepository;
    @Autowired
    private DesireService desireService;
    @Autowired
    private YearJobRepository yearJobRepository;

    public YearJobController.Response getYearPageResp(Long userId, List<Desire> desires, int startYear) {
        YearJobController.Response response = new YearJobController.Response();

        Calendar startCal = new GregorianCalendar();
        if (startYear == YearJobController.UNDEFINED_YEAR) {
            startYear = startCal.get(Calendar.YEAR) - 2;
        }

        startCal.set(Calendar.YEAR, startYear);
        Date startDate = startCal.getTime();

        for (Desire desire : desires) {
            YearJobController.DesireWithYear desireWithYear = new YearJobController.DesireWithYear();

            desireWithYear.setDesire(DesireDTO.of(desire));

            List<YearJobDTO> filteredYears = JobFilter.yearFilter(desire.getYears(), startDate, 5);
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
            YearJob newYearJob = new YearJob(jobDTO.getTitle(), jobDTO.getContent(), jobDTO.getFromTime(), jobDTO.getToTime(), maybeParentJob.get());
            YearJob entity = yearJobRepository.save(newYearJob);
            jobDTO.setId(entity.getId());
        } else {
            jobDTO = new JobDTO();
        }

        return jobDTO;
    }

    public JobDTO update(JobDTO dto, Long id) {
        Optional<YearJob> maybeEntity = yearJobRepository.findById(id);

        if (maybeEntity.isPresent()) {
            YearJob entity = maybeEntity.get();

            entity.setTitle(dto.getTitle());
            entity.setContent(dto.getContent());
            entity.setFromTime(dto.getFromTime());
            entity.setToTime(dto.getToTime());

            yearJobRepository.save(entity);
            dto.setId(id);
        } else {
            dto = new JobDTO();
        }

        return dto;
    }

    public JobDTO get(Long id) {
        JobDTO dto = new JobDTO();
        Optional<YearJob> maybeYearJob = yearJobRepository.findById(id);

        if (maybeYearJob.isPresent()) {
            YearJob entity = maybeYearJob.get();

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
            yearJobRepository.deleteById(id);
        } catch (Exception e) {
            id = -1l;
        }

        return id;
    }
}