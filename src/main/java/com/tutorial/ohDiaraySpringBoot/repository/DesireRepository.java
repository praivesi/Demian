package com.tutorial.ohDiaraySpringBoot.repository;

import com.tutorial.ohDiaraySpringBoot.model.Desire;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DesireRepository extends JpaRepository<Desire, Long> {
    @EntityGraph(attributePaths = {"jobs"})
    List<Desire> findAll();
}
