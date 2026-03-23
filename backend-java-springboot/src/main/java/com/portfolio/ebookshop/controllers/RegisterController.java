package com.portfolio.ebookshop.controllers;

import com.portfolio.ebookshop.models.User;
import com.portfolio.ebookshop.repos.UserRepository;
import com.portfolio.ebookshop.dto.RegistrationRequest; 

import jakarta.validation.Valid; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
public class RegisterController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public String handle(@Valid @RequestBody RegistrationRequest request) {
        //Hash password
        String hashed = passwordEncoder.encode(request.getPassword());
        //Map after DTO to database
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(hashed);
        newUser.setAddress(request.getAddress());

        userRepository.save(newUser);
        return "Registered";
    }

}