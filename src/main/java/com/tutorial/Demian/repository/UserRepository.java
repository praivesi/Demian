package com.tutorial.Demian.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorial.Demian.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = { "boards", "desires" })
    List<User> findAll();

    User findByUsername(String username);
}
