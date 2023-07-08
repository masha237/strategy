package ru.masha238.strategy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.masha238.strategy.domain.CommunityRole;
import ru.masha238.strategy.domain.CommunityRoleType;

import java.util.Optional;
import java.util.Set;

@Repository
public interface CommunityRoleRepository extends JpaRepository<CommunityRole, Long> {

    Optional<CommunityRoleType> findRoleTypesByUserId(Long userId);
}