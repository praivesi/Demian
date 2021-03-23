package com.tutorial.ohDiaraySpringBoot.service;

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
}
























