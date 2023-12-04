package com.vibrations.vibrationsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vibrations.vibrationsapi.model.User;

/**
 * JpaRepository for Storing/Retrieving User data objects from table
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // You can define custom query methods here if needed.
    User findByEmail(String email);
}
