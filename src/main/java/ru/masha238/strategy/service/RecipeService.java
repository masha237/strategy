package ru.masha238.strategy.service;

import org.springframework.stereotype.Service;
import ru.masha238.strategy.domain.Recipe;
import ru.masha238.strategy.domain.RecipeIngredientData;
import ru.masha238.strategy.domain.User;
import ru.masha238.strategy.form.RecipeForm;
import ru.masha238.strategy.repository.RecipeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final TagService tagService;
    private final IngredientService ingredientService;

    public RecipeService(RecipeRepository recipeRepository, TagService tagService, IngredientService ingredientService) {
        this.recipeRepository = recipeRepository;
        this.tagService = tagService;
        this.ingredientService = ingredientService;
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
        for (String s: recipeForm.getTags()) {
            tagService.addTag(s);
        }
        recipe.setTitle(recipeForm.getTitle());

        for (RecipeIngredientData s: recipeForm.getRecipeIngredientData()) {
            ingredientService.addIngredient(s.getIngredient());
        }

      /*  for (RecipeIngredientData s: recipeForm.getRecipeIngredientData()) {
         ????   recipe.setRecipeIngredients();
        }

        user.setCommunityRole(communityRoleRepository.findCommunityRoleByType(CommunityRoleType.USER).orElseThrow(() -> new UsernameNotFoundException("invalid role")));
      */  return null;
    }


}
