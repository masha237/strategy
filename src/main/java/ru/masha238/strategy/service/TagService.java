package ru.masha238.strategy.service;

import org.springframework.stereotype.Service;
import ru.masha238.strategy.domain.Recipe;
import ru.masha238.strategy.domain.Tag;
import ru.masha238.strategy.repository.RecipeRepository;
import ru.masha238.strategy.repository.TagRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }
    public Optional<Tag> findById(long id) {
        return tagRepository.findById(id);
    }
    public List<Tag> getAll() {
        return tagRepository.findAll();
    }

    public Optional<Tag> findByName(String tag) {
        return tagRepository.findByName(tag);
    }

    public void addTag(String tag) {
        tagRepository.findByName(tag).orElseGet(() -> {
            Tag newTag = new Tag();
            newTag.setName(tag);
            return tagRepository.save(newTag);
        });
    }

}
