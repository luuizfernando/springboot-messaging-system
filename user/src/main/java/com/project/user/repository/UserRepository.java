package com.project.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.user.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}