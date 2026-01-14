package com.recipe.recipeapp.service;

import com.recipe.recipeapp.dto.AuthResponse;
import com.recipe.recipeapp.dto.LoginRequest;
import com.recipe.recipeapp.dto.RegisterRequest;
import com.recipe.recipeapp.model.User;
import com.recipe.recipeapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public AuthResponse registerUser(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username is already in use");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email is already in use");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        User savedUser = userRepository.save(user);

        AuthResponse authResponse = new AuthResponse(user.getId(), savedUser.getUsername(),
                user.getPassword(),  savedUser.getEmail(), "Registered");
        return authResponse;
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail());

        if (user == null
                || !userRepository.existsByEmail(request.getEmail())
                || !user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        AuthResponse authResponse = new AuthResponse(user.getId(), user.getUsername(),
                user.getPassword(), user.getEmail(), "Login successful");

        return authResponse;
    }
}
