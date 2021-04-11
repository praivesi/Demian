package com.tutorial.Demian.repository;

import com.tutorial.Demian.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = { "boards", "desires" })
    List<User> findAll();

    User findByUsername(String username);
}
