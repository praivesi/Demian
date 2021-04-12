package com.tutorial.Demian.repository;

import com.tutorial.Demian.model.WeekJob;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeekJobRepository extends JpaRepository<WeekJob, Long> {
    @EntityGraph(attributePaths = {"dayJobs"})
    List<WeekJob> findAll();
}