package com.version1.frs.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.version1.frs.model.User;
import com.version1.frs.repository.UserRepository;

/**
 * Service implementation for loading user details. This class is responsible
 * for fetching user data from the database based on the email, which is then
 * used for authentication and authorization.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * Loads a user by their email address. This method is used by Spring Security
	 * during the authentication process.
	 *
	 * @param email the email of the user to be loaded
	 * @return a UserDetails object representing the authenticated user
	 * @throws UsernameNotFoundException if the user cannot be found with the given
	 *                                   email
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// Find the user by email from the repository
		User user = userRepository.findByUserEmail(email);

		// If the user doesn't exist, throw an exception
		if (user == null) {
			throw new UsernameNotFoundException("User not found with email: " + email);
		}

		// Return the UserDetails object containing the user's data
		return new UserDetailsImpl(user.getUserId(), user.getUserEmail(), user.getUserPassword(),
				Collections.singletonList(() -> "ROLE_" + user.getUserRole()));
	}
}
