package ru.masha238.strategy.service;

import org.springframework.stereotype.Service;
import ru.masha238.strategy.domain.Ingredient;
import ru.masha238.strategy.domain.Tag;
import ru.masha238.strategy.repository.IngredientRepository;
import ru.masha238.strategy.repository.TagRepository;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }
    public Optional<Ingredient> findById(long id) {
        return ingredientRepository.findById(id);
    }
    public List<Ingredient> getAll() {
        return ingredientRepository.findAll();
    }
    public Optional<Ingredient> findByName(String tag) {
        return ingredientRepository.findByName(tag);
    }

    public void addIngredient(String ingredient) {
        ingredientRepository.findByName(ingredient).orElseGet(() -> {
            Ingredient newIngredient = new Ingredient();
            newIngredient.setName(ingredient);
            return ingredientRepository.save(newIngredient);
        });
    }

}
