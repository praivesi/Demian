package com.tutorial.ohDiaraySpringBoot.repository;

import com.tutorial.ohDiaraySpringBoot.model.WeekJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayJobRepository extends JpaRepository<WeekJob, Long> {
}
