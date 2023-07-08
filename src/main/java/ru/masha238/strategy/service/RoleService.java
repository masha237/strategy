package ru.masha238.strategy.service;

import org.springframework.stereotype.Service;
import ru.masha238.strategy.domain.CommunityRole;
import ru.masha238.strategy.domain.RecipeRole;
import ru.masha238.strategy.domain.User;
import ru.masha238.strategy.repository.CommunityRoleRepository;
import ru.masha238.strategy.repository.RecipeRoleRepository;

import java.util.Optional;
import java.util.Set;

@Service
public class RoleService {
    private final RecipeRoleRepository recipeRoleRepository;
    private final CommunityRoleRepository communityRoleRepository;


    public RoleService(RecipeRoleRepository recipeRoleRepository, CommunityRoleRepository communityRoleRepository) {
        this.recipeRoleRepository = recipeRoleRepository;
        this.communityRoleRepository = communityRoleRepository;
    }

    public Optional<CommunityRole> findCommunityRoleById(long id) {
        return communityRoleRepository.findById(id);
    }

    public Optional<RecipeRole> findRecipeRoleById(long userId, long recipeId) {
        return recipeRoleRepository.findRoleTypesByUserIdAndRecipeId(userId, recipeId);
    }
}
