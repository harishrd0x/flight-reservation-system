package com.version1.frs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.version1.frs.dto.LoginRequest;
import com.version1.frs.dto.LoginResponse;
import com.version1.frs.dto.UserRequest;
import com.version1.frs.model.User;
import com.version1.frs.repository.UserRepository;
import com.version1.frs.security.JwtUtil;
import com.version1.frs.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    // Register endpoint
    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody UserRequest request) {
        try {
            String message = userService.register(request);
            return new ResponseEntity<>(new MessageResponse(message), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResponse("Registration failed: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    // Login endpoint
    @SuppressWarnings("unused")
	@PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserEmail(), request.getUserPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userRepository.findByUserEmail(request.getUserEmail());

            String token = jwtUtil.generateToken(user.getUserEmail(), user.getUserRole(), user.getUserId());
            return ResponseEntity.ok(new LoginResponse(token, user.getUserRole()));

        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>(new MessageResponse("Invalid email or password"), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResponse("Login failed: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Response wrapper for consistent frontend handling
    public static class MessageResponse {
        private String message;

        public MessageResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
