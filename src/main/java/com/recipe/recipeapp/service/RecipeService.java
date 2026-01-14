package com.recipe.recipeapp.service;

import com.recipe.recipeapp.dto.RecipeRequest;
import com.recipe.recipeapp.dto.RecipeResponse;
import com.recipe.recipeapp.model.Recipe;
import com.recipe.recipeapp.model.User;
import com.recipe.recipeapp.repository.RecipeRepository;
import com.recipe.recipeapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    public RecipeResponse createRecipe(RecipeRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Recipe recipe = new Recipe();
        recipe.setTitle(request.getTitle());
        recipe.setDescription(request.getDescription());
        recipe.setIngredients(request.getIngredients());
        recipe.setInstructions(request.getInstructions());
        recipe.setPrepTime(request.getPrepTime());
        recipe.setCookTime(request.getCookTime());
        recipe.setServings(request.getServings());
        recipe.setUser(user);

        Recipe savedRecipe = recipeRepository.save(recipe);
        return mapToResponse(savedRecipe);
    }

    public List<RecipeResponse> getAllRecipes() {
        return recipeRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public RecipeResponse getRecipeById(Long id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));
        return mapToResponse(recipe);
    }

    public List<RecipeResponse> getRecipesByUserId(Long userId) {
        return recipeRepository.findByUserId(userId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public RecipeResponse updateRecipe(Long id, RecipeRequest request) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        recipe.setTitle(request.getTitle());
        recipe.setDescription(request.getDescription());
        recipe.setIngredients(request.getIngredients());
        recipe.setInstructions(request.getInstructions());
        recipe.setPrepTime(request.getPrepTime());
        recipe.setCookTime(request.getCookTime());
        recipe.setServings(request.getServings());

        Recipe updatedRecipe = recipeRepository.save(recipe);
        return mapToResponse(updatedRecipe);
    }

    public void deleteRecipe(Long id) {
        if (!recipeRepository.existsById(id)) {
            throw new RuntimeException("Recipe not found");
        }
        recipeRepository.deleteById(id);
    }

    private RecipeResponse mapToResponse(Recipe recipe) {
        return new RecipeResponse(
                recipe.getId(),
                recipe.getTitle(),
                recipe.getDescription(),
                recipe.getIngredients(),
                recipe.getInstructions(),
                recipe.getPrepTime(),
                recipe.getCookTime(),
                recipe.getServings(),
                recipe.getUser().getUsername(),
                recipe.getUser().getId(),
                recipe.getCreatedAt(),
                recipe.getUpdatedAt()
        );
    }
}
