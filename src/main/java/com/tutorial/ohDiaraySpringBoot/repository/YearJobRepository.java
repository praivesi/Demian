package com.tutorial.ohDiaraySpringBoot.repository;

import com.tutorial.ohDiaraySpringBoot.model.YearJob;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
