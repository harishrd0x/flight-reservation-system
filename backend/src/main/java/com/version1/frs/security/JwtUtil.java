package com.version1.frs.security;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 * Utility class for handling JWT token generation, validation, and extraction
 * of claims. This class provides methods to generate, validate, and extract
 * information from JWT tokens.
 */
@Component
public class JwtUtil {

	// Secret key for signing JWT tokens. Should be securely stored and not
	// hardcoded in production.
	private static final String SECRET = "mySecretKeyForJwtTokenEncryption123456";

	// Key for HMAC SHA256 signing algorithm
	private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

	/**
	 * Generates a JWT token with the given email, role, and user ID.
	 *
	 * @param email  the user's email
	 * @param role   the user's role
	 * @param userId the user's ID
	 * @return the generated JWT token
	 */
	public String generateToken(String email, String role, Long userId) {
		return Jwts.builder().setSubject(email).claim("role", role).claim("userId", userId).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 5)) // 5 hrs expiration time
				.signWith(key, SignatureAlgorithm.HS256).compact();
	}

	/**
	 * Validates a JWT token by checking if the username in the token matches the
	 * provided email and if the token has not expired.
	 *
	 * @param token the JWT token to validate
	 * @param email the email to compare with the token's subject
	 * @return true if the token is valid, false otherwise
	 */
	public boolean validateToken(String token, String email) {
		return extractUsername(token).equals(email) && !isTokenExpired(token);
	}

	/**
	 * Checks if a JWT token has expired.
	 *
	 * @param token the JWT token to check
	 * @return true if the token has expired, false otherwise
	 */
	private boolean isTokenExpired(String token) {
		return extractAllClaims(token).getExpiration().before(new Date());
	}

	/**
	 * Extracts the username (subject) from the JWT token.
	 *
	 * @param token the JWT token from which to extract the username
	 * @return the username (email) stored in the token
	 */
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	/**
	 * Extracts the role from the JWT token.
	 *
	 * @param token the JWT token from which to extract the role
	 * @return the role stored in the token
	 */
	public String extractUserRole(String token) {
		return extractAllClaims(token).get("role", String.class);
	}

	/**
	 * Extracts the user ID from the JWT token.
	 *
	 * @param token the JWT token from which to extract the user ID
	 * @return the user ID stored in the token
	 */
	public Long extractUserId(String token) {
		return extractAllClaims(token).get("userId", Long.class);
	}

	/**
	 * Extracts a claim from the JWT token using the provided claims resolver.
	 *
	 * @param token          the JWT token from which to extract the claim
	 * @param claimsResolver the function to resolve the claim from the token
	 * @param <T>            the type of the claim
	 * @return the value of the extracted claim
	 */
	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		return claimsResolver.apply(extractAllClaims(token));
	}

	/**
	 * Extracts all claims from the JWT token.
	 *
	 * @param token the JWT token from which to extract claims
	 * @return the claims contained in the token
	 */
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
	}
}
