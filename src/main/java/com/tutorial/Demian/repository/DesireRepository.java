package com.tutorial.Demian.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorial.Demian.model.Desire;

public interface DesireRepository extends JpaRepository<Desire, Long> {
    @EntityGraph(attributePaths = {"decades"})
    List<Desire> findAll();

    List<Desire> findByUserId(Long userId);
}
