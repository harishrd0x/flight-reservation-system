package com.version1.frs.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.version1.frs.model.User;

/**
 * Custom implementation of the Spring Security UserDetails interface. This
 * class represents the user information needed for authentication and
 * authorization.
 */
public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	/**
	 * Constructor to initialize UserDetailsImpl with user information.
	 *
	 * @param id          the user's ID
	 * @param email       the user's email
	 * @param password    the user's password
	 * @param authorities the authorities/roles assigned to the user
	 */
	public UserDetailsImpl(Long id, String email, String password, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	/**
	 * Builds a UserDetailsImpl object from a User entity.
	 *
	 * @param user the User entity to create the UserDetailsImpl from
	 * @return a UserDetailsImpl object
	 */
	public static UserDetailsImpl build(User user) {
		return new UserDetailsImpl(user.getUserId(), user.getUserEmail(), user.getUserPassword(),
				Collections.singletonList(() -> "ROLE_" + user.getUserRole()));
	}

	/**
	 * Gets the user's ID.
	 *
	 * @return the user's ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Gets the username (email) of the user.
	 *
	 * @return the user's email
	 */
	@Override
	public String getUsername() {
		return email;
	}

	/**
	 * Gets the password of the user.
	 *
	 * @return the user's password
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/**
	 * Gets the authorities (roles) granted to the user.
	 *
	 * @return the user's authorities
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	/**
	 * Checks if the user's account is non-expired.
	 *
	 * @return true since the account is always considered non-expired
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * Checks if the user's account is non-locked.
	 *
	 * @return true since the account is always considered non-locked
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * Checks if the user's credentials (password) are non-expired.
	 *
	 * @return true since the credentials are always considered non-expired
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * Checks if the user's account is enabled.
	 *
	 * @return true since the account is always considered enabled
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}
}
