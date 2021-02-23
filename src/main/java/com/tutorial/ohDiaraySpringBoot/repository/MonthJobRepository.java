package com.tutorial.ohDiaraySpringBoot.repository;

import com.tutorial.ohDiaraySpringBoot.model.MonthJob;
import com.tutorial.ohDiaraySpringBoot.model.YearJob;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MonthJobRepository extends JpaRepository<MonthJob, Long> {
    @EntityGraph(attributePaths = {"weekJobs"})
    List<MonthJob> findAll();
}
