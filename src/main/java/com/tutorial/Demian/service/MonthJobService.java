package com.tutorial.Demian.service;

import com.tutorial.Demian.dto.DesireDTO;
import com.tutorial.Demian.dto.JobDTO;
import com.tutorial.Demian.dto.MonthJobDTO;
import com.tutorial.Demian.dto.MonthPageDTO;
import com.tutorial.Demian.model.Desire;
import com.tutorial.Demian.model.MonthJob;
import com.tutorial.Demian.repository.DesireRepository;
import com.tutorial.Demian.repository.MonthJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MonthJobService {
    @Autowired
    private DesireRepository desireRepository;
    @Autowired
    private MonthJobRepository monthJobRepository;

    public List<MonthPageDTO> get(Date startDate, Long userId) {
        List<Desire> desires = desireRepository.findByUserId(userId);
        List<MonthPageDTO> monthPages = new ArrayList<>();

        for (Desire desire : desires) {
            MonthPageDTO monthPage = new MonthPageDTO();
            monthPage.setDesire(DesireDTO.of(desire));

            List<MonthJobDTO> months = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(startDate);

                cal.add(Calendar.MONTH, 1 * i);
                cal.set(Calendar.DAY_OF_MONTH, 1);
                cal.set(Calendar.HOUR, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 1);
                Date curStartDate = cal.getTime();

                cal.add(Calendar.MONTH, 1);
                cal.add(Calendar.SECOND, -1);

                Date curEndDate = cal.getTime();

                MonthJob matchedMonth = null;
                for (MonthJob month : desire.getMonths()) {
                    if (curStartDate.getTime() <= month.getFromTime().getTime() &&
                            month.getToTime().getTime() <= curEndDate.getTime()) {
                        matchedMonth = month;
                        break;
                    }
                }

                if (matchedMonth == null) {
                    MonthJobDTO tmpMonthJobDTO = new MonthJobDTO();
                    tmpMonthJobDTO.setId(-1l);
                    months.add(tmpMonthJobDTO);
                } else {
                    months.add(MonthJobDTO.of(matchedMonth));
                }
            }

            monthPage.setMonths(months);
            monthPages.add(monthPage);
        }

        return monthPages;
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
