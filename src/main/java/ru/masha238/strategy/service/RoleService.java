package ru.masha238.strategy.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.masha238.strategy.domain.CommunityRole;
import ru.masha238.strategy.domain.CommunityRoleType;
import ru.masha238.strategy.domain.RecipeRole;
import ru.masha238.strategy.domain.User;
import ru.masha238.strategy.repository.CommunityRoleRepository;
import ru.masha238.strategy.repository.RecipeRoleRepository;
import ru.masha238.strategy.repository.UserRepository;

import java.util.Optional;

@Service
public class RoleService {
    private final RecipeRoleRepository recipeRoleRepository;
    private final CommunityRoleRepository communityRoleRepository;
    private final UserRepository userRepository;


    public RoleService(RecipeRoleRepository recipeRoleRepository, CommunityRoleRepository communityRoleRepository,
                       UserRepository userRepository) {
        this.recipeRoleRepository = recipeRoleRepository;
        this.communityRoleRepository = communityRoleRepository;
        this.userRepository = userRepository;
    }

    public Optional<CommunityRole> findCommunityRoleById(long id) {
        return communityRoleRepository.findById(id);
    }

    public Optional<RecipeRole> findRecipeRoleById(long userId, long recipeId) {
        return recipeRoleRepository.findRoleTypesByUserIdAndRecipeId(userId, recipeId);
    }


}
