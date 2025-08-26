package com.example.Billing.System.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Billing.System.repository.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
