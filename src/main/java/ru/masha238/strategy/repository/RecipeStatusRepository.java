package ru.masha238.strategy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.masha238.strategy.domain.CommunityRole;
import ru.masha238.strategy.domain.CommunityRoleType;
import ru.masha238.strategy.domain.RecipeStatus;
import ru.masha238.strategy.domain.RecipeStatusType;

import java.util.Optional;

@Repository
public interface RecipeStatusRepository extends JpaRepository<RecipeStatus, Long> {

    Optional<RecipeStatus> findRecipeStatusByType(RecipeStatusType recipeStatusType);
}