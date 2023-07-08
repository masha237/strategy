package ru.masha238.strategy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.masha238.strategy.domain.User;
import ru.masha238.strategy.exception.ValidationException;
import ru.masha238.strategy.form.LoginForm;
import ru.masha238.strategy.form.RegisterForm;
import ru.masha238.strategy.service.RoleService;
import ru.masha238.strategy.service.UserService;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("userRecipes")
    public ResponseEntity<?> getUserRecipes(@RequestParam String login) {
        User user = userService.findByLogin(login).orElseThrow(() -> new ValidationException("invalid login or password"));
        return ResponseEntity.ok(user.getRecipes());
    }

    @GetMapping("user")
    public String currentUserName(final Authentication auth) {
        return ((User) auth.getPrincipal()).getName();
    }

    @GetMapping("users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("userRole")
    public ResponseEntity<?> getUserRole(@RequestParam String login) {
        User user = userService.findByLogin(login).orElseThrow(() -> new ValidationException("invalid login or password"));
        roleService.findCommunityRoleById(user.getId());
        return ResponseEntity.ok(roleService.findCommunityRoleById(user.getId()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody final LoginForm loginForm) {
        Optional<User> user = userService.validateUser(loginForm.getLogin(), loginForm.getPassword());
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody final RegisterForm request) {
        User registeredUser = userService.registerUser(request);
        return ResponseEntity.ok(registeredUser);
    }


}
