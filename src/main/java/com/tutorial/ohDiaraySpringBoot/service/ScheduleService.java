package com.tutorial.ohDiaraySpringBoot.service;

import com.tutorial.ohDiaraySpringBoot.dto.DecadeJobDTO;
import com.tutorial.ohDiaraySpringBoot.dto.DesireWithDecadeJobDTO;
import com.tutorial.ohDiaraySpringBoot.dto.JobDTO;
import com.tutorial.ohDiaraySpringBoot.model.*;
import com.tutorial.ohDiaraySpringBoot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    private DesireRepository desireRepository;
    @Autowired
    private DecadeJobRepository decadeJobRepository;
    @Autowired
    private YearJobRepository yearJobRepository;
    @Autowired
    private MonthJobRepository monthJobRepository;
    @Autowired
    private WeekJobRepository weekJobRepository;
    @Autowired
    private DayJobRepository dayJobRepository;

    public List<DesireWithDecadeJobDTO> GetAllDesires()
    {
        List<Desire> desires = desireRepository.findAll();
        List<DesireWithDecadeJobDTO> response = new ArrayList<>();

        for(Desire desire : desires){
            response.add(DesireWithDecadeJobDTO.of(desire, desire.getDecadeJobs()));
        }

        return response;
    }

    public JobDTO save(JobDTO jobDTO) {
        switch (jobDTO.getJobType()){
            case 0: // Decade
                Desire parentDesire = desireRepository.findById(jobDTO.getParentId()).get(); // TODO: Need exception handling
                DecadeJob decade = new DecadeJob(jobDTO.getTitle(), jobDTO.getContent(),
                        jobDTO.getFromTime(), jobDTO.getToTime(), parentDesire);

                decadeJobRepository.save(decade);
                break;
            case 1: // Year
                DecadeJob parentDecade = decadeJobRepository.findById(jobDTO.getParentId()).get();
                YearJob year = new YearJob(jobDTO.getTitle(), jobDTO.getContent(),
                        new Timestamp(jobDTO.getFromTime().getTime()),
                        new Timestamp(jobDTO.getToTime().getTime()),
                        parentDecade);

                yearJobRepository.save(year);
                break;
            case 2: // Month
                YearJob parentYear = yearJobRepository.findById(jobDTO.getParentId()).get();
                MonthJob month = new MonthJob(jobDTO.getTitle(), jobDTO.getContent(),
                        new Timestamp(jobDTO.getFromTime().getTime()),
                        new Timestamp(jobDTO.getToTime().getTime()),
                        parentYear);

                monthJobRepository.save(month);
                break;
            case 3: // Week
                MonthJob parentMonth = monthJobRepository.findById(jobDTO.getParentId()).get();
                WeekJob week = new WeekJob(jobDTO.getTitle(), jobDTO.getContent(),
                        new Timestamp(jobDTO.getFromTime().getTime()),
                        new Timestamp(jobDTO.getToTime().getTime()),
                        parentMonth);

                weekJobRepository.save(week);
                break;
            case 4: // Day
                WeekJob parentWeek = weekJobRepository.findById(jobDTO.getParentId()).get();
                DayJob day = new DayJob(jobDTO.getTitle(), jobDTO.getContent(),
                        new Timestamp(jobDTO.getFromTime().getTime()),
                        new Timestamp(jobDTO.getToTime().getTime()),
                        parentWeek);

                dayJobRepository.save(day);
                break;
        }

        return jobDTO;
    }

    public JobDTO get(Long id, int jobType) {
        JobDTO dto = new JobDTO();
        dto.setJobType(jobType);

        switch (jobType){
            case 0: // Decade
                DecadeJob decadeJob = decadeJobRepository.findById(id).get();

                dto.setId(decadeJob.getId());
                dto.setTitle(decadeJob.getTitle());
                dto.setContent(decadeJob.getContent());
                dto.setFromTime(decadeJob.getFromTime());
                dto.setToTime(decadeJob.getToTime());
                dto.setParentId(decadeJob.getDesire().getId());

                break;
            case 1: // Year
                YearJob yearJob = yearJobRepository.findById(id).get();

                dto.setId(yearJob.getId());
                dto.setTitle(yearJob.getTitle());
                dto.setContent(yearJob.getContent());
                dto.setFromTime(yearJob.getFromTime());
                dto.setToTime(yearJob.getToTime());
                dto.setParentId(yearJob.getDecadeJob().getId());

                break;
            case 2: // Month
                MonthJob monthJob = monthJobRepository.findById(id).get();

                dto.setId(monthJob.getId());
                dto.setTitle(monthJob.getTitle());
                dto.setContent(monthJob.getContent());
                dto.setFromTime(monthJob.getFromTime());
                dto.setToTime(monthJob.getToTime());
                dto.setParentId(monthJob.getYearJob().getId());

                break;
            case 3: // Week
                WeekJob weekJob = weekJobRepository.findById(id).get();

                dto.setId(weekJob.getId());
                dto.setTitle(weekJob.getTitle());
                dto.setContent(weekJob.getContent());
                dto.setFromTime(weekJob.getFromTime());
                dto.setToTime(weekJob.getToTime());
                dto.setParentId(weekJob.getMonthJob().getId());

                break;
            case 4: // Day
                DayJob dayJob = dayJobRepository.findById(id).get();

                dto.setId(dayJob.getId());
                dto.setTitle(dayJob.getTitle());
                dto.setContent(dayJob.getContent());
                dto.setFromTime(dayJob.getFromTime());
                dto.setToTime(dayJob.getToTime());
                dto.setParentId(dayJob.getWeekJob().getId());

                break;
        }

        return dto;
    }

    public void delete(Long id, int jobType){
        switch (jobType){
            case 0: // Decade
                decadeJobRepository.deleteById(id);
                break;
            case 1: // Year
                yearJobRepository.deleteById(id);
                break;
            case 2: // Month
                monthJobRepository.deleteById(id);
                break;
            case 3: // Week
                weekJobRepository.deleteById(id);
                break;
            case 4: // Day
                dayJobRepository.deleteById(id);
                break;
        }
    }
}
























