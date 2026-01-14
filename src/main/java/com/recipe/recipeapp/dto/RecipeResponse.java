package com.recipe.recipeapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RecipeResponse {
    private Long id;
    private String title;
    private String description;
    private String ingredients;
    private String instructions;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String username;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
