package ru.masha238.strategy.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "community_role")
public class CommunityRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @OneToMany(mappedBy = "communityRole", orphanRemoval = true)
    private List<User> users = new ArrayList<>();

    @Enumerated(STRING)
    private CommunityRoleType type;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }


    public CommunityRoleType getType() {
        return type;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setType(CommunityRoleType type) {
        this.type = type;
    }
}