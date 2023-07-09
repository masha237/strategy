package ru.masha238.strategy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.masha238.strategy.domain.CommunityRole;
import ru.masha238.strategy.domain.CommunityRoleType;
import ru.masha238.strategy.domain.RecipeRole;
import ru.masha238.strategy.domain.User;
import ru.masha238.strategy.exception.AccessException;
import ru.masha238.strategy.exception.ValidationException;
import ru.masha238.strategy.service.RoleService;
import ru.masha238.strategy.service.UserService;
import ru.masha238.strategy.utils.Utils;
import java.util.Set;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/userRecipes")
    public ResponseEntity<?> getUserRecipes(@RequestParam String login) {
        User user = userService.findByLogin(login).orElseThrow(() -> new ValidationException("invalid login or password"));
        return ResponseEntity.ok(user.getRecipes());
    }

    @GetMapping("/user")
    public String currentUserName(final Authentication auth) {
        return Utils.getCurrentUser(auth).getUsername();
    }


    public boolean checkCommunityRole(Set<CommunityRoleType> roleTypeSet, Authentication auth) {
        CommunityRole communityRoleType = roleService.findCommunityRoleById(Utils.getCurrentUser(auth).getId()).orElseThrow(() -> new ValidationException("invalid login or password"));
        return roleTypeSet.contains(communityRoleType.getType());
    }


    @GetMapping("/users")
    public ResponseEntity<?> getUsers(Authentication auth) {
        if (!checkCommunityRole(Set.of(CommunityRoleType.ADMIN), auth)) {
            throw new AccessException("you hasn't access to /userRole");
        }
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/userRole")
    public ResponseEntity<?> getUserRole(@RequestParam String login, Authentication auth) {
        if (!checkCommunityRole(Set.of(CommunityRoleType.ADMIN), auth)) {
            throw new AccessException("you hasn't access to /userRole");
        }
        User user = userService.findByLogin(login).orElseThrow(() -> new ValidationException("invalid login or password"));
        roleService.findCommunityRoleById(user.getId());
        return ResponseEntity.ok(roleService.findCommunityRoleById(user.getId()));
    }

    @PostMapping("/setUserRole")
    public ResponseEntity<?> setUserRole(@RequestParam String login, @RequestParam String role, Authentication auth) {
        if (!checkCommunityRole(Set.of(CommunityRoleType.ADMIN), auth)) {
            throw new AccessException("you hasn't access to /userRole");
        }
        User user = userService.findByLogin(login).orElseThrow(() -> new ValidationException("invalid login or password"));
        CommunityRole communityRole = roleService.findCommunityRoleById(user.getId()).orElseThrow(() -> new ValidationException("invalid role"));
        roleService.changeRoleUser(communityRole, CommunityRoleType.valueOf(role));
        return ResponseEntity.ok(user);
    }

}
