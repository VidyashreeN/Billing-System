package com.example.Billing.System.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Billing.System.Repository.entity.User;

// This file is part of the Billing System project.
// It defines the UserRepository interface for managing User entities.
// The UserRepository interface extends JpaRepository to provide CRUD operations for User entities.
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository provides methods like save, findById, findAll, deleteById, etc.
    // No additional methods are defined here, but you can add custom query methods if needed.
    // Additional query methods can be defined here if needed

}
