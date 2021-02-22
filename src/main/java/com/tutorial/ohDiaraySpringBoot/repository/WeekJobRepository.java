package com.tutorial.ohDiaraySpringBoot.repository;

import com.tutorial.ohDiaraySpringBoot.model.MonthJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeekJobRepository extends JpaRepository<MonthJob, Long> {
}
