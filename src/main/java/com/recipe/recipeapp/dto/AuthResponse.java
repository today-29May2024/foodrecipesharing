package com.recipe.recipeapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthResponse {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String message;

    public AuthResponse(Long id, String username, String password, String email, String message) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.message = message;
    }

}
