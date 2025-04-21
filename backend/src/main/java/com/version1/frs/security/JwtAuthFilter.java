package com.version1.frs.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * JWT Authentication Filter that processes incoming HTTP requests. This filter
 * intercepts requests, validates the JWT token, and sets the security context
 * if the token is valid.
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * Filters incoming requests, extracting the JWT token from the Authorization
	 * header. If the token is valid, it sets the authentication in the security
	 * context.
	 *
	 * @param request     the incoming HTTP request
	 * @param response    the HTTP response
	 * @param filterChain the filter chain to pass the request along to the next
	 *                    filter
	 * @throws ServletException if an error occurs during filtering
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain) throws ServletException, IOException {

		final String authHeader = request.getHeader("Authorization");
		String userEmail = null;
		String jwtToken = null;

		// Example header: Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR...
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			jwtToken = authHeader.substring(7);
			userEmail = jwtUtil.extractUsername(jwtToken);
		}

		if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
			if (jwtUtil.validateToken(jwtToken, userDetails.getUsername())) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());

				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}

		filterChain.doFilter(request, response);
	}

	/**
	 * Determines whether the filter should not be applied to the current request.
	 * In this case, it avoids filtering on the login endpoint.
	 *
	 * @param request the incoming HTTP request
	 * @return true if the filter should not be applied, false otherwise
	 * @throws ServletException if an error occurs during filtering
	 */
	@Override
	protected boolean shouldNotFilter(@NonNull HttpServletRequest request) throws ServletException {
		return request.getServletPath().equals("/api/login");
	}

}
