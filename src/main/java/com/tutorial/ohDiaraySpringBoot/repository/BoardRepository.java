package com.tutorial.ohDiaraySpringBoot.repository;

import com.tutorial.ohDiaraySpringBoot.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
