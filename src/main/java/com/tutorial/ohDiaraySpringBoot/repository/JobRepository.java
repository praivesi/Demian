package com.tutorial.ohDiaraySpringBoot.repository;

import com.tutorial.ohDiaraySpringBoot.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
