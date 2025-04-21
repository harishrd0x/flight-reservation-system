package com.version1.frs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.version1.frs.model.User;

/**
 * Repository interface for managing {@link User} entities. Provides CRUD
 * operations and custom queries related to users.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Retrieves a user by their email. Used for registration and authentication
	 * processes.
	 *
	 * @param userEmail the email of the user
	 * @return the {@link User} with the given email, or null if no user found
	 */
	User findByUserEmail(String userEmail);

	/**
	 * Retrieves a user by their ID. This is an optional method for future use cases
	 * where we need to fetch users by ID.
	 *
	 * @param userId the ID of the user
	 * @return an {@link Optional} containing the user if found, or an empty
	 *         {@link Optional} if not
	 */
	@NonNull
	Optional<User> findById(@NonNull Long userId);
}
