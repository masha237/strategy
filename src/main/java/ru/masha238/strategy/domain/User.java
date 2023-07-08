package ru.masha238.strategy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @NotEmpty
    @Size(min = 1, max = 100)
    @Column(unique = true, nullable = false)
    private String login;

    @NotEmpty
    @Size(min = 1, max = 1000)
    private String password;

    @NotEmpty
    @Size(min = 1, max = 100)
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    @JsonIgnore
    @OrderBy("creationTime desc")
    private List<Recipe> recipes = new ArrayList<>();

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRecipes(List<Recipe> posts) {
        this.recipes = posts;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public long getId() {
        return id;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }
}
