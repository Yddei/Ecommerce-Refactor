package com.portfolio.ebookshop.services;

import com.portfolio.ebookshop.models.User;
import com.portfolio.ebookshop.repos.UserRepository;

import com.portfolio.ebookshop.dto.LoginRequest;
import com.portfolio.ebookshop.dto.RegistrationRequest;

import com.portfolio.ebookshop.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    //REGISTER
    public void registerUser(RegistrationRequest requestDto) throws Exception {

        //Check duplicate
        if (userRepository.findByEmailIgnoreCase(requestDto.getEmail()) != null ) {
            throw new Exception("Email already in use");
        }

        //Hash password
        String hashed = passwordEncoder.encode(requestDto.getPassword());

        //Map DTO to Entity
        User newUser = new User();
        newUser.setUsername(requestDto.getUsername());
        newUser.setEmail(requestDto.getEmail());
        newUser.setPassword(hashed);
        newUser.setAddress(requestDto.getAddress());

        userRepository.save(newUser);
    }

    //LOGIN
    public String loginUser(LoginRequest loginDto) throws Exception {

        //find user
        User existingUser = userRepository.findByEmailIgnoreCase(loginDto.getEmail());
        
        //invalid credentials
        if (existingUser == null) {
            throw new Exception("Invalid credentials");
        }
        
        //match hashes
        boolean isPasswordMatch = passwordEncoder.matches(loginDto.getPassword(), existingUser.getPassword());

        //pass
        if (isPasswordMatch) {
            return jwtService.generateToken(existingUser.getEmail());
        }
        //fail
        else {
           throw new Exception("Invalid credentials");
        }
        
    }
}
