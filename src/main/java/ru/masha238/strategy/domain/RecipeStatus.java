package ru.masha238.strategy.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "recipe_status")
public class RecipeStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(STRING)
    private RecipeStatusType type;

    @OneToMany(mappedBy = "recipeStatus", orphanRemoval = true)
    @JsonIgnore
    @OrderBy("id desc")
    private List<Recipe> recipes = new ArrayList<>();

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setType(RecipeStatusType type) {
        this.type = type;
    }


    public RecipeStatusType getType() {
        return type;
    }
}