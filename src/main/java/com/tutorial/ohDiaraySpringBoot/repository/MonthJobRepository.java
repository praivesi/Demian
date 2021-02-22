package com.tutorial.ohDiaraySpringBoot.repository;

import com.tutorial.ohDiaraySpringBoot.model.YearJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthJobRepository extends JpaRepository<YearJob, Long> {
}
