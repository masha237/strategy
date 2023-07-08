package ru.masha238.strategy.domain;


import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "recipe_role")
public class RecipeStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @Enumerated(STRING)
    private RecipeStatusType type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void setType(RecipeStatusType type) {
        this.type = type;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public RecipeStatusType getType() {
        return type;
    }
}