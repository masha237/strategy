package ru.masha238.strategy.utils;

import org.springframework.security.core.Authentication;
import ru.masha238.strategy.domain.User;

public class Utils {
    public static User getCurrentUser(final Authentication auth) {
        return ((User) auth.getPrincipal());
    }
}
