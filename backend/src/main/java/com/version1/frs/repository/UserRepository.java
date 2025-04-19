package com.version1.frs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.version1.frs.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Find a user by their email (still needed for registration and authentication)
    User findByUserEmail(String userEmail);
    
    // Optional: Method to find a user by their ID (in case it's needed in future operations)
	@NonNull
	Optional<User> findById(@NonNull Long userId);
}
