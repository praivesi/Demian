package com.tutorial.Demian.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorial.Demian.model.Decade;

public interface DecadeRepository extends JpaRepository<Decade, Long> {
    @EntityGraph(attributePaths = {"yearJobs"})
    List<Decade> findAll();
}
