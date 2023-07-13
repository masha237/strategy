package ru.masha238.strategy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.masha238.strategy.domain.CommunityRoleType;
import ru.masha238.strategy.domain.RecipeIngredient;

import java.util.Optional;

@Repository
public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {

}