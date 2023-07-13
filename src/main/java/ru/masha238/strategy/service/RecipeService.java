package ru.masha238.strategy.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.masha238.strategy.domain.*;
import ru.masha238.strategy.exception.ValidationException;
import ru.masha238.strategy.form.RecipeForm;
import ru.masha238.strategy.repository.RecipeIngredientRepository;
import ru.masha238.strategy.repository.RecipeRepository;
import ru.masha238.strategy.repository.RecipeStatusRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final TagService tagService;
    private final IngredientService ingredientService;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final RecipeStatusRepository recipeStatusRepository;

    public RecipeService(RecipeRepository recipeRepository, TagService tagService, IngredientService ingredientService, RecipeIngredientRepository recipeIngredientRepository, RecipeStatusRepository recipeStatusRepository) {
        this.recipeRepository = recipeRepository;
        this.tagService = tagService;
        this.ingredientService = ingredientService;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.recipeStatusRepository = recipeStatusRepository;
    }

    public Optional<Recipe> findById(long id) {
        return recipeRepository.findById(id);
    }

    public List<Recipe> getRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe write(RecipeForm recipeForm, User currentUser) {


        Recipe recipe = new Recipe();
        recipe.setUser(currentUser);
        recipe.setTitle(recipeForm.getTitle());
        recipe.setDescription(recipeForm.getDescription());
        Set<Tag> tags = new HashSet<>();
        for (String s: recipeForm.getTags()) {
            tagService.addTag(s);
            tags.add(tagService.findByName(s).orElseThrow(() -> new ValidationException("invalid tag name")));
        }
        recipe.setTags(Set.of());
        recipe.setTitle(recipeForm.getTitle());
        for (RecipeIngredientData s: recipeForm.getRecipeIngredientData()) {
            ingredientService.addIngredient(s.getIngredient());
        }
        recipe.setRecipeStatus(recipeStatusRepository.findRecipeStatusByType(RecipeStatusType.NOT_VERIFIED).orElseThrow(() -> new UsernameNotFoundException("invalid status")));
        recipeRepository.save(recipe);

        for (RecipeIngredientData s: recipeForm.getRecipeIngredientData()) {
            RecipeIngredient recipeIngredient = new RecipeIngredient();
            recipeIngredient.setIngredient(ingredientService.findByName(s.getIngredient()).orElseThrow(() -> new ValidationException("invalid ingredient name")));
            recipeIngredient.setAmount(s.getAmount());
            recipeIngredient.setRecipe(recipe);
            recipeIngredientRepository.save(recipeIngredient);
        }
        return recipe;
    }


}
