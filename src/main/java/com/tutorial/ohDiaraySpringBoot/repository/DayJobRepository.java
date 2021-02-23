package com.tutorial.ohDiaraySpringBoot.repository;

import com.tutorial.ohDiaraySpringBoot.model.DayJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DayJobRepository extends JpaRepository<DayJob, Long> {
    List<DayJob> findAll();
}
