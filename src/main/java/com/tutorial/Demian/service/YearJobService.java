package com.tutorial.Demian.service;

import com.tutorial.Demian.dto.DesireDTO;
import com.tutorial.Demian.dto.JobDTO;
import com.tutorial.Demian.dto.YearJobDTO;
import com.tutorial.Demian.dto.YearPageDTO;
import com.tutorial.Demian.model.Desire;
import com.tutorial.Demian.model.YearJob;
import com.tutorial.Demian.repository.DesireRepository;
import com.tutorial.Demian.repository.YearJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class YearJobService {
    @Autowired
    private DesireRepository desireRepository;
    @Autowired
    private YearJobRepository yearJobRepository;

    public List<YearPageDTO> get(Date startDate) {
        List<Desire> desires = desireRepository.findAll();
        List<YearPageDTO> yearPages = new ArrayList<>();

        for (Desire desire : desires) {
            YearPageDTO yearPage = new YearPageDTO();
            yearPage.setDesire(DesireDTO.of(desire));

            List<YearJobDTO> years = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(startDate);

                cal.add(Calendar.YEAR, 1 * i);
                Date curStartDate = cal.getTime();

                cal.add(Calendar.YEAR, 1);
                cal.add(Calendar.SECOND, -1);
                Date curEndDate = cal.getTime();

                YearJob matchedYear = null;
                for (YearJob year : desire.getYears()) {
                    if (curStartDate.getTime() <= year.getFromTime().getTime() &&
                            year.getToTime().getTime() <= curEndDate.getTime()) {
                        matchedYear = year;
                        break;
                    }
                }

                if (matchedYear == null) {
                    YearJobDTO tmpYearJobDTO = new YearJobDTO();
                    tmpYearJobDTO.setId(-1l);
                    years.add(tmpYearJobDTO);
                } else {
                    years.add(YearJobDTO.of(matchedYear));
                }
            }

            yearPage.setYears(years);
            yearPages.add(yearPage);
        }

        return yearPages;
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