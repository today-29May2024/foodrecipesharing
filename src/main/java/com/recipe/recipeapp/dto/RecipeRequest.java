package com.recipe.recipeapp.dto;

import lombok.Data;

@Data
public class RecipeRequest {
    private String title;
    private String description;
    private String ingredients;
    private String instructions;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private Long userId;
}
