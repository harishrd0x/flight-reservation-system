//package com.version1.frs.controller;
//
//import com.version1.frs.dto.LoginRequest;
//import com.version1.frs.dto.LoginResponse;
//import com.version1.frs.model.User;
//import com.version1.frs.repository.UserRepository;
//import com.version1.frs.security.JwtUtil;
//
//import jakarta.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api")
//@CrossOrigin
//public class LoginController {
//
//    @Autowired
//    private AuthenticationManager authManager;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
//        try {
//            Authentication authentication = authManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                    request.getUserEmail(), request.getUserPassword()
//                )
//            );
//
//            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//
//            // Fetch user to get user role
//            User user = userRepository.findByUserEmail(request.getUserEmail());
//            if (user == null) {
//                return ResponseEntity.status(404).body("User not found.");
//            }
//
//            String token = jwtUtil.generateToken(user.getUserEmail(), user.getUserRole());
//            return ResponseEntity.ok(new LoginResponse(token, user.getUserRole()));
//        } catch (BadCredentialsException ex) {
//            return ResponseEntity.status(401).body("Invalid email or password");
//        }
//    }
//}