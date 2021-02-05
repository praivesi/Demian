package com.tutorial.ohDiaraySpringBoot.repository;

import com.tutorial.ohDiaraySpringBoot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
