package com.portfolio.ebookshop.controllers;

import com.portfolio.ebookshop.models.User;
import com.portfolio.ebookshop.repos.UserRepository;
import com.portfolio.ebookshop.dto.LoginRequest;
import com.portfolio.ebookshop.dto.RegistrationRequest; 

import jakarta.validation.Valid; 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //REGISTER
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationRequest requestDto) {
        //Check duplicate
        if (userRepository.findByEmailIgnoreCase(requestDto.getEmail()) != null ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already registered");
        }
        //Hash password
        String hashed = passwordEncoder.encode(requestDto.getPassword());
        //Map after DTO to database
        User newUser = new User();
        newUser.setUsername(requestDto.getUsername());
        newUser.setEmail(requestDto.getEmail());
        newUser.setPassword(hashed);
        newUser.setAddress(requestDto.getAddress());

        userRepository.save(newUser);
        return ResponseEntity.ok("Registered successfully");
    }

    //LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginDto) {
        
        //using /repos/UserRepository.java:
        User existingUser = userRepository.findByEmailIgnoreCase(loginDto.getEmail());
        
        //invalid credentials
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
        
        //match hashes
        boolean isPasswordMatch = passwordEncoder.matches(loginDto.getPassword(), existingUser.getPassword());

        //pass
        if (isPasswordMatch) {
            return ResponseEntity.ok("Login successful"); 
        }
        //fail
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}