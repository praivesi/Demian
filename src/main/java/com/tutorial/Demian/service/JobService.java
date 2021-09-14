package com.tutorial.Demian.service;

import com.tutorial.Demian.dto.JobDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {
    @Autowired
    private DesireService desireService;
    @Autowired
    private DecadeGrowthService decadeGrowthService;
    @Autowired
    private YearGrowthService yearGrowthService;
    @Autowired
    private MonthGrowthService monthGrowthService;

    public JobDTO save(JobDTO dto) {
        JobDTO response = new JobDTO();

        switch (dto.getJobType()) {
            case 0: // Decade
                response = decadeGrowthService.save(dto);
                break;

            case 1: // Year
                response = yearGrowthService.save(dto);
                break;

            case 2: // Month
                response = monthGrowthService.save(dto);
                break;
        }

        return response;
    }

    public JobDTO update(JobDTO dto, Long id, int jobType) {
        JobDTO response = new JobDTO();

        switch (jobType) {
            case 0: // Decade
                response = decadeGrowthService.update(dto, id);
                break;

            case 1: // Year
                response = yearGrowthService.update(dto, id);
                break;

            case 2: // Month
                response = monthGrowthService.update(dto, id);
                break;
        }

        return response;
    }

    public JobDTO get(Long id, int jobType) {
        JobDTO response = null;

        switch (jobType) {
            case 0: // Decade
                response = decadeGrowthService.get(id);
                break;

            case 1: // Year
                response = yearGrowthService.get(id);
                break;

            case 2: // Month
                response = monthGrowthService.get(id);
                break;
        }

        return response;
    }

    public Long delete(Long id, int jobType) {
        Long response = -1l;

        switch (jobType) {
            case 0: // Decade
                response = decadeGrowthService.delete(id);
                break;

            case 1: // Year
                response = yearGrowthService.delete(id);
                break;

            case 2: // Month
                response = monthGrowthService.delete(id);
                break;
        }

        return response;
    }
}
























