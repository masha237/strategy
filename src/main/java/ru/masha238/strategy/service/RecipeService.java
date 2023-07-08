package ru.masha238.strategy.service;

import org.springframework.stereotype.Service;
import ru.masha238.strategy.domain.Recipe;
import ru.masha238.strategy.domain.User;
import ru.masha238.strategy.repository.RecipeRepository;
import ru.masha238.strategy.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Optional<Recipe> findById(long id) {
        return recipeRepository.findById(id);
    }

    public List<Recipe> getRecipes() {
        return recipeRepository.findAll();
    }

}
