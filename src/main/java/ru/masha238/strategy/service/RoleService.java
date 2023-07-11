package ru.masha238.strategy.service;

import org.springframework.stereotype.Service;
import ru.masha238.strategy.domain.CommunityRole;
import ru.masha238.strategy.repository.CommunityRoleRepository;
import ru.masha238.strategy.repository.UserRepository;

import java.util.Optional;

@Service
public class RoleService {
   private final CommunityRoleRepository communityRoleRepository;
    private final UserRepository userRepository;


    public RoleService(CommunityRoleRepository communityRoleRepository,
                       UserRepository userRepository) {
        this.communityRoleRepository = communityRoleRepository;
        this.userRepository = userRepository;
    }

    public Optional<CommunityRole> findCommunityRoleById(long id) {
        return communityRoleRepository.findById(id);
    }
}
