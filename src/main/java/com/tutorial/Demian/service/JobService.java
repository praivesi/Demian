package com.tutorial.Demian.service;

import com.tutorial.Demian.dto.JobDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {
    @Autowired
    private DesireService desireService;
    @Autowired
    private DecadeService decadeService;
    @Autowired
    private YearService yearService;
    @Autowired
    private MonthService monthService;

    public JobDTO save(JobDTO dto) {
        JobDTO response = new JobDTO();

        switch (dto.getJobType()) {
            case 0: // Decade
                response = decadeService.save(dto);
                break;

            case 1: // Year
                response = yearService.save(dto);
                break;

            case 2: // Month
                response = monthService.save(dto);
                break;
        }

        return response;
    }

    public JobDTO update(JobDTO dto, Long id, int jobType) {
        JobDTO response = new JobDTO();

        switch (jobType) {
            case 0: // Decade
                response = decadeService.update(dto, id);
                break;

            case 1: // Year
                response = yearService.update(dto, id);
                break;

            case 2: // Month
                response = monthService.update(dto, id);
                break;
        }

        return response;
    }

    public JobDTO get(Long id, int jobType) {
        JobDTO response = null;

        switch (jobType) {
            case 0: // Decade
                response = decadeService.get(id);
                break;

            case 1: // Year
                response = yearService.get(id);
                break;

            case 2: // Month
                response = monthService.get(id);
                break;
        }

        return response;
    }

    public Long delete(Long id, int jobType) {
        Long response = -1l;

        switch (jobType) {
            case 0: // Decade
                response = decadeService.delete(id);
                break;

            case 1: // Year
                response = yearService.delete(id);
                break;

            case 2: // Month
                response = monthService.delete(id);
                break;
        }

        return response;
    }
}
























