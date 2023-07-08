package ru.masha238.strategy.domain;



import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

public class Tag {
    @NotEmpty
    @Size(min = 1, max = 100)
    @Column(unique = true, nullable = false)
    private String name;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    @ManyToMany
    @JoinTable(name = "post_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<User> tags = new LinkedHashSet<>();
}
