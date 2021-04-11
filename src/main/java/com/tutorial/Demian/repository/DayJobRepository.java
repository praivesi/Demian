package com.tutorial.Demian.repository;

import com.tutorial.Demian.model.DayJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DayJobRepository extends JpaRepository<DayJob, Long> {
    List<DayJob> findAll();
}
