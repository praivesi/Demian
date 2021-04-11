package com.tutorial.Demian.repository;

import com.tutorial.Demian.model.MonthJob;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MonthJobRepository extends JpaRepository<MonthJob, Long> {
    @EntityGraph(attributePaths = {"weekJobs"})
    List<MonthJob> findAll();
}
