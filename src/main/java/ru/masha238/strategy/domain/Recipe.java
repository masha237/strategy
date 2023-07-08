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
    @Column(unique = true, nullable = false)
    private String title;

    @NotEmpty
    @Size(min = 1, max = 32)
    private String text;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    private Date creationTime;

    @ManyToMany
    @JoinTable(name = "recipes_tags",
            joinColumns = @JoinColumn(name = "recipes_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<User> tags = new LinkedHashSet<>();


    @OneToMany(mappedBy = "recipe", orphanRemoval = true)
    @JsonIgnore
    @OrderBy("creationTime desc")
    private List<Comment> comments = new ArrayList<>();
}
