package com.version1.frs.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.version1.frs.dto.LoginRequest;
import com.version1.frs.dto.UserRequest;
import com.version1.frs.dto.UserResponse;
import com.version1.frs.model.User;
import com.version1.frs.model.Wallet;
import com.version1.frs.repository.UserRepository;
import com.version1.frs.security.UserDetailsImpl;
import com.version1.frs.service.UserService;

/**
 * Implementation of the {@link UserService} interface. Provides methods for
 * managing users, including registration, authentication, updating, retrieving
 * user information, and deleting accounts.
 */
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	// Constructor injection for dependencies
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * Registers a new user in the system.
	 * 
	 * @param request the user registration request containing user details
	 * @return a message indicating the registration status
	 */
	@Override
	public String register(UserRequest request) {
		if (userRepository.findByUserEmail(request.getUserEmail()) != null) {
			return "Email already registered.";
		}

		User user = new User();
		user.setUserName(request.getUserName());
		user.setUserEmail(request.getUserEmail());
		user.setUserGender(request.getUserGender());
		// Encode password before saving
		user.setUserPassword(passwordEncoder.encode(request.getUserPassword()));
		user.setUserRole(request.getUserRole() != null ? request.getUserRole() : "CUSTOMER");

		Wallet wallet = new Wallet();
		wallet.setUser(user);
		wallet.setBalance(BigDecimal.ZERO);
		user.setWallet(wallet); // creating bidirectional link

		userRepository.save(user);
		return "Registration successful.";
	}

	/**
	 * Authenticates a user based on provided login credentials.
	 * 
	 * @param request the login request containing the user's email and password
	 * @return the authenticated {@link User} if credentials are valid, or null if
	 *         invalid
	 */
	@Override
	public User authenticate(LoginRequest request) {
		User user = userRepository.findByUserEmail(request.getUserEmail());
		if (user != null && passwordEncoder.matches(request.getUserPassword(), user.getUserPassword())) {
			return user;
		}
		return null;
	}

	/**
	 * Retrieves all users in the system.
	 * 
	 * @return a list of {@link UserResponse} DTOs containing user information
	 */
	@Override
	public List<UserResponse> getAllUsers() {
		return userRepository.findAll().stream().map(this::mapToResponse).toList();
	}

	/**
	 * Retrieves user information by user ID.
	 * 
	 * @param id the ID of the user to fetch
	 * @return an {@link Optional} containing the user response, if found
	 */
	@Override
	public Optional<UserResponse> getUserById(Long id) {
		Optional<User> userOpt = userRepository.findById(id);
		return userOpt.map(this::mapToResponse); // Converts User to UserResponse inside Optional
	}

	/**
	 * Updates user information based on the provided request.
	 * 
	 * @param userRequest the user request containing the updated user details
	 * @return the updated {@link UserResponse} DTO
	 */
	@Override
	public UserResponse updateUser(UserRequest userRequest) {
		// Get the currently authenticated user from the token
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long userId = ((UserDetailsImpl) userDetails).getId(); // Get userId from the token

		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		// Update the user details if provided in the request
		if (userRequest.getUserName() != null) {
			user.setUserName(userRequest.getUserName());
		}
		if (userRequest.getUserGender() != null) {
			user.setUserGender(userRequest.getUserGender());
		}
		if (userRequest.getUserPassword() != null) {
			user.setUserPassword(passwordEncoder.encode(userRequest.getUserPassword()));
		}
		if (userRequest.getUserRole() != null) {
			user.setUserRole(userRequest.getUserRole());
		}

		userRepository.save(user);
		return mapToResponse(user);
	}

	/**
	 * Deletes the currently authenticated user's account.
	 */
	@Override
	public void deleteAccount() {
		// Get the currently authenticated user from the token
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long userId = ((UserDetailsImpl) userDetails).getId(); // Get userId from the token

		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		userRepository.delete(user);
	}

	/**
	 * Maps a {@link User} entity to a {@link UserResponse} DTO.
	 * 
	 * @param user the user entity to convert
	 * @return the corresponding {@link UserResponse} DTO
	 */
	private UserResponse mapToResponse(User user) {
		UserResponse res = new UserResponse();
		res.setUserId(user.getUserId());
		res.setUserName(user.getUserName());
		res.setUserEmail(user.getUserEmail());
		res.setUserGender(user.getUserGender());
		res.setUserRole(user.getUserRole());
		return res;
	}
}
