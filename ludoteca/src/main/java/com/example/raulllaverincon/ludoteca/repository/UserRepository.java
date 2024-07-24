package com.example.raulllaverincon.ludoteca.repository;

import com.example.raulllaverincon.ludoteca.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find a user by username
    Optional<User> findByUsername(String username);

    // Find all users with a specific role
    List<User> findByRole(String role);

    // Find all users who are penalized
    List<User> findByPenalized(boolean penalized);

}

