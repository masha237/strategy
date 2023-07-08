package ru.masha238.strategy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.masha238.strategy.domain.Recipe;
import ru.masha238.strategy.domain.User;
import ru.masha238.strategy.exception.ValidationException;
import ru.masha238.strategy.service.RecipeService;

import java.util.List;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("{recipeId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getRecipes(@PathVariable String recipeId) {
        Recipe recipe = recipeService.findById(Long.parseLong(recipeId)).orElseThrow(() -> new ValidationException("invalid recipe id"));
        return ResponseEntity.ok(recipe);
    }

    @GetMapping("recipes")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getRecipes() {
        List<Recipe> recipes = recipeService.getRecipes();
        return ResponseEntity.ok(recipes);
    }
}
