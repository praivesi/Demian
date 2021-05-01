package com.tutorial.Demian.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorial.Demian.model.Month;

public interface MonthRepository extends JpaRepository<Month, Long> {
    @EntityGraph(attributePaths = {"weekJobs"})
    List<Month> findAll();
}
