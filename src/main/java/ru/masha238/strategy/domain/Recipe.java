package ru.masha238.strategy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "recipes")
public class Recipe {
    @NotEmpty
    @Size(min = 1, max = 100)
    private String title;

    @NotEmpty
    @Size(min = 1, max = 10000)
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    private Date creationTime;


    @OneToMany(mappedBy = "recipe", orphanRemoval = true)
    @JsonIgnore
    @OrderBy("creationTime desc")
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "recipe_status_id")

    private RecipeStatus recipeStatus;

    @OneToMany(mappedBy = "recipe", orphanRemoval = true)
    @OrderBy("id desc")
    @JsonIgnore
    private List<RecipeIngredient> recipeIngredients = new ArrayList<>();

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getTitle() {
        return title;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public RecipeStatus getRecipeStatus() {
        return recipeStatus;
    }

    public void setRecipeStatus(RecipeStatus recipeStatus) {
        this.recipeStatus = recipeStatus;
    }

    @ManyToMany
    @JoinTable(name = "recipes_tags",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "tags_id"))
    @OrderBy("name desc")
    @JsonIgnore
    private Set<Tag> tags = new LinkedHashSet<>();

    public List<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
