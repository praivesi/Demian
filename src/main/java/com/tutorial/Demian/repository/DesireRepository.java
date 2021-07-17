package com.tutorial.Demian.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorial.Demian.model.DesireGrowth;

public interface DesireRepository extends JpaRepository<DesireGrowth, Long> {
    @EntityGraph(attributePaths = {"decades"})
    List<DesireGrowth> findAll();

    List<DesireGrowth> findByUserId(Long userId);
}
