package com.example.Billing.System.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Billing.System.Repository.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
