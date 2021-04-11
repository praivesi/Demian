package com.tutorial.Demian.repository;

import com.tutorial.Demian.model.YearJob;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface YearJobRepository extends JpaRepository<YearJob, Long> {
    @EntityGraph(attributePaths = {"monthJobs"})
    List<YearJob> findAll();

    /*
    {
        "title": "mi",
        "content": "con",
        "fromTime": "2007-09-23T01:10:10.000+00:00",
        "toTime": "2007-09-23T01:10:10.000+00:00",
        "parentId": 1,
        "jobType": 1
    }
    */
}
