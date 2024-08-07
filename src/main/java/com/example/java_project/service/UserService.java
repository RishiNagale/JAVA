package com.example.java_project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.java_project.entity.User;
import com.example.java_project.repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	// Fetch the list of all users
    public List<User> getUserList() {
        return userRepo.findAll();
    }

    // Create a new user with encoded password
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    // Fetch a user by id
    public User fetchUser(Long id) {
        Optional<User> userOpt = userRepo.findById(id);
        return userOpt.orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    // Delete a user by id
    public void deleteUser(Long id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    // Update an existing user
    public User updateUser(Long id, User userDetails) {
        User existingUser = fetchUser(id);
        existingUser.setName(userDetails.getName());
        existingUser.setEmail(userDetails.getEmail());
        existingUser.setUserType(userDetails.getUserType());
        
        if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }

        return userRepo.save(existingUser);
    }
	
	
	
}
