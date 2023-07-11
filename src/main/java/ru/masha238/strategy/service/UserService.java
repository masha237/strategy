package ru.masha238.strategy.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.masha238.strategy.domain.CommunityRoleType;
import ru.masha238.strategy.domain.User;
import ru.masha238.strategy.exception.ValidationException;
import ru.masha238.strategy.form.RegisterForm;
import ru.masha238.strategy.repository.CommunityRoleRepository;
import ru.masha238.strategy.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final CommunityRoleRepository communityRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, CommunityRoleRepository communityRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.communityRoleRepository = communityRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }


    public Optional<User> validateUser(final String login, final String password) {
        return userRepository.findByLogin(login).filter(user -> passwordEncoder.matches(password, user.getPassword()));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }


    public User registerUser(final RegisterForm request) {
        if (!userRepository.findByLogin(request.getLogin()).isEmpty()) {
            throw new ValidationException("Not unique login");
        }

        if (request.getUsername().length() > 100) {
            throw new ValidationException("Name too long");
        }
        if (request.getLogin().length() > 100) {
            throw new ValidationException("Login too long");
        }
        if (request.getPassword().length() > 256) {
            throw new ValidationException("Password too long");
        }

        User user = new User();
        user.setLogin(request.getLogin());
        user.setUsername(request.getLogin());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getUsername());
        user.setCommunityRole(communityRoleRepository.findCommunityRoleByType(CommunityRoleType.USER).orElseThrow(() -> new UsernameNotFoundException("invalid role")));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public void changeRoleUser(User user, CommunityRoleType communityRoleType) {
        user.setCommunityRole(communityRoleRepository.findCommunityRoleByType(communityRoleType).orElseThrow(() -> new UsernameNotFoundException("invalid role")));
        userRepository.save(user);
    }
}
