package com.tutorial.ohDiaraySpringBoot.repository;

import com.tutorial.ohDiaraySpringBoot.model.YearJob;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface YearJobRepository extends JpaRepository<YearJob, Long> {
    @EntityGraph(attributePaths = {"monthJobs"})
    List<YearJob> findAll();
}
