package com.tutorial.Demian.repository;

import com.tutorial.Demian.model.Month;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MonthRepository extends JpaRepository<Month, Long> {
    @EntityGraph(attributePaths = {"weekJobs"})
    List<Month> findAll();
}
