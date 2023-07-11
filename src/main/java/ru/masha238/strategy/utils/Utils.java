package ru.masha238.strategy.utils;

import org.springframework.security.core.Authentication;
import ru.masha238.strategy.domain.CommunityRoleType;
import ru.masha238.strategy.domain.User;

import java.util.Set;

public class Utils {
    public static User getCurrentUser(final Authentication auth) {
        return ((User) auth.getPrincipal());
    }

    public static boolean checkCommunityRole(Set<CommunityRoleType> roleTypeSet, Authentication auth) {
        CommunityRoleType communityRoleType = Utils.getCurrentUser(auth).getCommunityRole().getType();
        return roleTypeSet.contains(communityRoleType);
    }
}
