package com.tutorial.Demian.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorial.Demian.model.Year;

public interface YearRepository extends JpaRepository<Year, Long> {
    @EntityGraph(attributePaths = {"monthJobs"})
    List<Year> findAll();
}
