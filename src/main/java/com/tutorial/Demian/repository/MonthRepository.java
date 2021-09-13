package com.tutorial.Demian.repository;

import java.util.List;

import com.tutorial.Demian.model.MonthGrowth;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthRepository extends JpaRepository<MonthGrowth, Long> {
    @EntityGraph(attributePaths = {"weekJobs"})
    List<MonthGrowth> findAll();
}
