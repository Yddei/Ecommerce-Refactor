package com.portfolio.ebookshop.controllers;

import com.portfolio.ebookshop.services.UserService;
import com.portfolio.ebookshop.dto.LoginRequest;
import com.portfolio.ebookshop.dto.RegistrationRequest; 

import jakarta.validation.Valid; 

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    //REGISTER
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationRequest requestDto) {
        try {
            userService.registerUser(requestDto);
            return ResponseEntity.ok("Registered");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginDto) {
        try {
            String token = userService.loginUser(loginDto);

            Map<String, String> response = new HashMap<>();
            response.put("token", token);

            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}