package com.hungryhero.controller;

// import com.hungryhero.Users;

import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hungryhero.model.Users;
import com.hungryhero.service.UsersService;

import jakarta.persistence.EntityNotFoundException;

import java.util.Map;
import java.util.List;

@RestController

@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    

    private final UsersService usersService;

    // @Autowired
    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    ///Get All Users
    @GetMapping("/users")
    public List<Users> find() {
        return usersService.find();
    }

    // Get a user by ID
    @GetMapping("/users/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        Optional<Users> userOptional = usersService.findById(id);

        // Check if the user exists
        Users user = userOptional.orElse(null);
    
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
    
        // Return the user as the response
        return ResponseEntity.ok(user);
    }

    // Create a new user
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Users> createUser(@RequestBody Users newUser) {
        Users savedUser = usersService.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        boolean authenticated = usersService.authenticateUser(email, password);

        if (authenticated) {
            return ResponseEntity.ok("Authentication successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }

    // Update an existing user
    @PutMapping("/users/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody Users updatedUserRequest) {
        Users existingUser = usersService.update(id, updatedUserRequest);

        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(existingUser);
    }

    // Delete a user by ID
    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            usersService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex) {
            // return ResponseEntity.notFound().build();
            return ResponseEntity.internalServerError().build();
        }
    }
}