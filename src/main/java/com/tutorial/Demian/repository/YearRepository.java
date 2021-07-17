package com.tutorial.Demian.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorial.Demian.model.YearGrowth;

public interface YearRepository extends JpaRepository<YearGrowth, Long> {
    @EntityGraph(attributePaths = {"monthJobs"})
    List<YearGrowth> findAll();
}
