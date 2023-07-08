package ru.masha238.strategy.domain;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

public enum CommunityRoleType {
    ADMIN, MODERATOR, VERIFIED_USER;
}