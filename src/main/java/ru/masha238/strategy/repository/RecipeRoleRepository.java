package ru.masha238.strategy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.masha238.strategy.domain.RecipeRole;
import ru.masha238.strategy.domain.RecipeRoleType;

import java.util.Optional;
import java.util.Set;
@Repository
public interface RecipeRoleRepository extends JpaRepository<RecipeRole, Long> {

    Optional<RecipeRole> findRoleTypesByUserIdAndRecipeId(Long userId, Long recipeId);
}