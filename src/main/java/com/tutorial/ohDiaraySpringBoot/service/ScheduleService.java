package com.tutorial.ohDiaraySpringBoot.service;

import com.tutorial.ohDiaraySpringBoot.dto.DesireWithDecadeJobDTO;
import com.tutorial.ohDiaraySpringBoot.dto.JobDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    private DesireService desireService;
    @Autowired
    private DecadeJobService decadeJobService;
    @Autowired
    private YearJobService yearJobService;
    @Autowired
    private MonthJobService monthJobService;
    @Autowired
    private WeekJobService weekJobService;
    @Autowired
    private DayJobService dayJobService;

    public List<DesireWithDecadeJobDTO> GetAllDesires() {
        return null;
//        List<Desire> desires = desireRepository.findAll();
//        List<DesireWithDecadeJobDTO> response = new ArrayList<>();
//
//        for(Desire desire : desires){
//            response.add(DesireWithDecadeJobDTO.of(desire, desire.getDecadeJobs()));
//        }
//
//        return response;
    }

    public JobDTO save(JobDTO dto) {
        JobDTO response = null;

        switch (dto.getJobType()) {
            case 0: // Decade
                response = decadeJobService.save(dto);
                break;

            case 1: // Year
                response = yearJobService.save(dto);
                break;

            case 2: // Month
                response = monthJobService.save(dto);
                break;

            case 3: // Week
                response = weekJobService.save(dto);
                break;

            case 4: // Day
                response = dayJobService.save(dto);
                break;
        }

        return response;
    }

    public JobDTO get(Long id, int jobType) {
        JobDTO response = null;

        switch (jobType) {
            case 0: // Decade
                response = decadeJobService.get(id);
                break;

            case 1: // Year
                response = yearJobService.get(id);
                break;

            case 2: // Month
                response = weekJobService.get(id);
                break;

            case 3: // Week
                response = monthJobService.get(id);
                break;

            case 4: // Day
                response = dayJobService.get(id);
                break;
        }

        return response;
    }

    public Long delete(Long id, int jobType) {
        Long response = -1l;

        switch (jobType) {
            case 0: // Decade
                response = decadeJobService.delete(id);
                break;

            case 1: // Year
                response = yearJobService.delete(id);
                break;

            case 2: // Month
                response = monthJobService.delete(id);
                break;

            case 3: // Week
                response = weekJobService.delete(id);
                break;

            case 4: // Day
                response = dayJobService.delete(id);
                break;
        }

        return response;
    }
}
























