package com.tutorial.Demian.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorial.Demian.model.DecadeGrowth;

public interface DecadeRepository extends JpaRepository<DecadeGrowth, Long> {
    @EntityGraph(attributePaths = {"yearJobs"})
    List<DecadeGrowth> findAll();
}
