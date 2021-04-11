package com.tutorial.Demian.repository;

import com.tutorial.Demian.model.Desire;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DesireRepository extends JpaRepository<Desire, Long> {
    @EntityGraph(attributePaths = {"decadeJobs"})
    List<Desire> findAll();
}
