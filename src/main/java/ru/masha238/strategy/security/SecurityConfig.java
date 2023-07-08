package ru.masha238.strategy.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public AuthenticationManager authenticationManager(final AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        // TODO
        http.authorizeRequests()
                .antMatchers("/api/user/**").hasAnyAuthority("ROLE_ADMIN", "USER", "ROLE_VERIFIED_USER", "ROLE_MODERATOR")
                .antMatchers("/api/recipes/**").hasAnyAuthority("ROLE_ADMIN","USER", "ROLE_VERIFIED_USER", "ROLE_MODERATOR")
                .antMatchers("/api/auth/**").permitAll()
              //  .antMatchers("/api/**/**").permitAll()
                .and().httpBasic();
        http.cors().and().csrf().disable();
        return http.build();
    }
    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }
}