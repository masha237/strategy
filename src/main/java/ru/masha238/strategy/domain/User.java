package ru.masha238.strategy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @NotEmpty
    @Size(min = 1, max = 100)
    @Column(unique = true, nullable = false)
    private String login;

    @NotEmpty
    @Size(min = 1, max = 1000)
    private String password;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @NotEmpty
    @Size(min = 1, max = 100)
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "community_role_id")
    private CommunityRole communityRole;

    public void setUsername(String name) {
        this.login = name;
    }

    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    @JsonIgnore
    @OrderBy("creationTime desc")
    private List<Recipe> recipes = new ArrayList<>();

    public CommunityRole getCommunityRole() {
        return communityRole;
    }

    public void setCommunityRole(CommunityRole communityRole) {
        this.communityRole = communityRole;
    }

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


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(communityRole.getType().name()));
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

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "user_id")
    @OrderBy("creationTime desc")
    @JsonIgnore
    private List<Recipe> favourite = new ArrayList<>();

    public List<Recipe> getFavourite() {
        return favourite;
    }

    public void setFavourite(List<Recipe> favourite) {
        this.favourite = favourite;
    }
}
