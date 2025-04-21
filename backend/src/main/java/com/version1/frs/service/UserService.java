package com.version1.frs.service;

import java.util.List;
import java.util.Optional;

import com.version1.frs.dto.LoginRequest;
import com.version1.frs.dto.UserRequest;
import com.version1.frs.dto.UserResponse;
import com.version1.frs.model.User;

/**
 * Service interface for managing user accounts and authentication in the
 * system. It includes methods for registering users, updating user details,
 * deleting accounts, and authenticating users.
 */
public interface UserService {

	// -------------------- Read --------------------

	/**
	 * Retrieves all users in the system.
	 * 
	 * @return a list of {@link UserResponse} DTOs representing all users
	 */
	List<UserResponse> getAllUsers();

	/**
	 * Retrieves a user by their ID.
	 * 
	 * @param id the ID of the user to retrieve
	 * @return an {@link Optional} containing the {@link UserResponse} DTO if the
	 *         user is found, empty otherwise
	 */
	Optional<UserResponse> getUserById(Long id);

	// -------------------- Create --------------------

	/**
	 * Registers a new user in the system.
	 * 
	 * @param request the {@link UserRequest} DTO containing the user details
	 *                (username, email, password, etc.)
	 * @return a message indicating the success or failure of the registration
	 */
	String register(UserRequest request);

	// -------------------- Update --------------------

	/**
	 * Updates the details of an existing user.
	 * 
	 * @param userRequest the {@link UserRequest} DTO containing the updated user
	 *                    details
	 * @return the updated {@link UserResponse} DTO
	 * @throws RuntimeException if the user is not found
	 */
	UserResponse updateUser(UserRequest userRequest);

	// -------------------- Delete --------------------

	/**
	 * Deletes the currently authenticated user's account.
	 * 
	 * @throws RuntimeException if the user is not found
	 */
	void deleteAccount();

	// -------------------- Authentication --------------------

	/**
	 * Authenticates a user based on the provided login credentials.
	 * 
	 * @param request the {@link LoginRequest} DTO containing the login credentials
	 *                (email, password)
	 * @return the {@link User} if authentication is successful, null otherwise
	 */
	User authenticate(LoginRequest request);
}
