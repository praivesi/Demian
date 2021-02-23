package com.tutorial.ohDiaraySpringBoot.repository;

import com.tutorial.ohDiaraySpringBoot.model.DecadeJob;
import com.tutorial.ohDiaraySpringBoot.model.MonthJob;
import com.tutorial.ohDiaraySpringBoot.model.WeekJob;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeekJobRepository extends JpaRepository<WeekJob, Long> {
    @EntityGraph(attributePaths = {"dayJobs"})
    List<WeekJob> findAll();
}
