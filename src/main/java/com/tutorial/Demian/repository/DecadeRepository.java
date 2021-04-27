package com.tutorial.Demian.repository;

import com.tutorial.Demian.model.Decade;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DecadeRepository extends JpaRepository<Decade, Long> {
    @EntityGraph(attributePaths = {"yearJobs"})
    List<Decade> findAll();
}
