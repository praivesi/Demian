package com.tutorial.ohDiaraySpringBoot.repository;

import com.tutorial.ohDiaraySpringBoot.model.DecadeJob;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DecadeJobRepository extends JpaRepository<DecadeJob, Long> {
    @EntityGraph(attributePaths = {"yearJobs"})
    List<DecadeJob> findAll();
}
