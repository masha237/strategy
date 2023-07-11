package ru.masha238.strategy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.masha238.strategy.domain.CommunityRoleType;
import ru.masha238.strategy.domain.Recipe;
import ru.masha238.strategy.domain.Tag;
import ru.masha238.strategy.domain.User;
import ru.masha238.strategy.exception.AccessException;
import ru.masha238.strategy.exception.ValidationException;
import ru.masha238.strategy.form.RecipeForm;
import ru.masha238.strategy.repository.TagRepository;
import ru.masha238.strategy.service.RecipeService;
import ru.masha238.strategy.service.TagService;

import java.util.List;
import java.util.Set;

import static ru.masha238.strategy.utils.Utils.checkCommunityRole;
import static ru.masha238.strategy.utils.Utils.getCurrentUser;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {
    private final RecipeService recipeService;
    private final TagService tagService;

    public RecipeController(RecipeService recipeService, TagService tagService) {
        this.recipeService = recipeService;
        this.tagService = tagService;
    }

    @GetMapping("{recipeId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getRecipe(@PathVariable String recipeId, final Authentication auth) {
        if (!checkCommunityRole(Set.of(CommunityRoleType.ADMIN, CommunityRoleType.MODERATOR, CommunityRoleType.VERIFIED_USER), auth)) {
            throw new AccessException("you hasn't access to /userRole");
        }
        Recipe recipe = recipeService.findById(Long.parseLong(recipeId)).orElseThrow(() -> new ValidationException("invalid recipe id"));
        return ResponseEntity.ok(recipe);
    }

    @GetMapping("recipes")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getRecipes(final Authentication auth) {
        if (!checkCommunityRole(Set.of(CommunityRoleType.ADMIN, CommunityRoleType.MODERATOR, CommunityRoleType.VERIFIED_USER), auth)) {
            throw new AccessException("you hasn't access to /userRole");
        }
        List<Recipe> recipes = recipeService.getRecipes();
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("tags")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getTags(final Authentication auth) {
        if (!checkCommunityRole(Set.of(CommunityRoleType.ADMIN, CommunityRoleType.MODERATOR, CommunityRoleType.VERIFIED_USER), auth)) {
            throw new AccessException("you hasn't access to /userRole");
        }
        List<Tag> recipes = tagService.getAll();
        return ResponseEntity.ok(recipes);
    }

    @PostMapping("write")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> write(@RequestBody RecipeForm recipeForm, final Authentication auth) {
        if (!checkCommunityRole(Set.of(CommunityRoleType.ADMIN, CommunityRoleType.MODERATOR, CommunityRoleType.VERIFIED_USER), auth)) {
            throw new AccessException("you hasn't access to /userRole");
        }
        Recipe recipe = recipeService.write(recipeForm, getCurrentUser(auth));
        return ResponseEntity.ok(recipe);
    }
}
