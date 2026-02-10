package com.project.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.project.app.model.User;

public interface UserRepository extends JpaRepository<User,String>{
    UserDetails findByLogin(String login);
} 