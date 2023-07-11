package ru.masha238.strategy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.masha238.strategy.domain.CommunityRole;
import ru.masha238.strategy.domain.CommunityRoleType;
import ru.masha238.strategy.domain.User;
import ru.masha238.strategy.exception.AccessException;
import ru.masha238.strategy.exception.ValidationException;
import ru.masha238.strategy.form.UserRoleForm;
import ru.masha238.strategy.repository.CommunityRoleRepository;
import ru.masha238.strategy.service.RoleService;
import ru.masha238.strategy.service.UserService;
import ru.masha238.strategy.utils.Utils;
import java.util.Set;

import static ru.masha238.strategy.utils.Utils.checkCommunityRole;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final CommunityRoleRepository communityRoleRepository;

    public UserController(UserService userService, RoleService roleService, CommunityRoleRepository communityRoleRepository) {
        this.userService = userService;
        this.roleService = roleService;
        this.communityRoleRepository = communityRoleRepository;
    }

    @GetMapping("/userRecipes")
    public ResponseEntity<?> getUserRecipes(@RequestParam String login, final Authentication auth) {
        if (!checkCommunityRole(Set.of(CommunityRoleType.ADMIN, CommunityRoleType.MODERATOR, CommunityRoleType.VERIFIED_USER), auth)) {
            throw new AccessException("you hasn't access to /userRole");
        }
        User user = userService.findByLogin(login).orElseThrow(() -> new ValidationException("invalid login or password"));
        return ResponseEntity.ok(user.getRecipes());
    }

    @GetMapping("/user")
    public String currentUserName(final Authentication auth) {
        return Utils.getCurrentUser(auth).getUsername();
    }



    @GetMapping("/users")
    public ResponseEntity<?> getUsers(Authentication auth) {
        if (!checkCommunityRole(Set.of(CommunityRoleType.ADMIN), auth)) {
            throw new AccessException("you hasn't access to /userRole" + roleService.findCommunityRoleById(Utils.getCurrentUser(auth).getCommunityRole().getId()).orElseThrow(() -> new ValidationException("invalid login or password")).getType());
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
    public ResponseEntity<?> setUserRole(@RequestBody UserRoleForm userRoleForm, Authentication auth) {
        if (!checkCommunityRole(Set.of(CommunityRoleType.ADMIN), auth)) {
            throw new AccessException("you hasn't access to /userRole");
        }
        User user = userService.findByLogin(userRoleForm.getLogin()).orElseThrow(() -> new ValidationException("invalid login or password"));
        userService.changeRoleUser(user, CommunityRoleType.valueOf(userRoleForm.getRole()));
        return ResponseEntity.ok(user);
    }

}
