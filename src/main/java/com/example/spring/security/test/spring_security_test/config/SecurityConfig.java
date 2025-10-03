package com.example.spring.security.test.spring_security_test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.authorization.AuthorizationManagers;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        AuthorizationManager<RequestAuthorizationContext> adminRoles = AuthorizationManagers.anyOf(
                AuthorityAuthorizationManager.hasRole("ADMIN")
        );
        AuthorizationManager<RequestAuthorizationContext> userRoles = AuthorizationManagers.anyOf(
                AuthorityAuthorizationManager.hasRole("USER")
        );

        return
                http
                        .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                                .requestMatchers("/api/v1/public").permitAll()
                                .requestMatchers("/api/v1/admin").access(adminRoles)
                                .requestMatchers("/api/v1/user").access(userRoles)
                        .anyRequest().authenticated())
                        .formLogin(Customizer.withDefaults())
                        .build();
    }

    @Bean
    UserDetailsService userDetailsService() {
        UserDetails admin = User.withUsername("admin").password("{noop}admin").roles("ADMIN").build();
        UserDetails user  = User.withUsername("user").password("{noop}user").roles("USER").build();
        return new InMemoryUserDetailsManager(admin, user);
    }
}
