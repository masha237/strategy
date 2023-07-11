package ru.masha238.strategy.form;

import ru.masha238.strategy.domain.RecipeIngredientData;

import java.util.List;

public class RecipeForm {
    private String title;
    private String description;
    private List<String> tags;
    private List<RecipeIngredientData> recipeIngredientData;

    public RecipeForm() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<RecipeIngredientData> getRecipeIngredientData() {
        return recipeIngredientData;
    }

    public void setRecipeIngredientData(List<RecipeIngredientData> recipeIngredientData) {
        this.recipeIngredientData = recipeIngredientData;
    }
}
