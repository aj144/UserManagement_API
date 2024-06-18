package com.example.UserManagement.API.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.UserManagement.API.Entity.User_info;
import com.example.UserManagement.API.exception.UserNotFoundException;
import com.example.UserManagement.API.repository.UserRepository;




@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
     UserRepository  userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
  
@PostMapping("/register")
public ResponseEntity<String> registerUser(@RequestBody User_info user) {
    try {
    	
    	  String encodedPassword = passwordEncoder.encode(user.getPassword());
          user.setPassword(encodedPassword);
          userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    } catch (DataIntegrityViolationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username or email already exists");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to	 register user");
    }
}

    @GetMapping("/fetch")
    public ResponseEntity<?> fetchUserDetails(@RequestParam String username) {
    	System.out.println("helll");
        try {
            Optional<User_info> userOptional = userRepository.findByUsername(username);
            if (userOptional.isPresent()) {
                return ResponseEntity.ok(userOptional.get());
            } else {
                throw new UserNotFoundException(username);
            }
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch user details");
        }
            }
}
