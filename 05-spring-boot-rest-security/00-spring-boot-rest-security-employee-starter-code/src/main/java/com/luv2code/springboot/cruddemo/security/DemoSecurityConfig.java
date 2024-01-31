package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static com.luv2code.springboot.cruddemo.security.Luv2codeRole.*;

// @Configuration is a class-level annotation indicating that an object is a source of bean definitions.
@Configuration
public class DemoSecurityConfig {

    // InMemoryUserDetailsManager => Implements UserDetailsService to provide support for username/password based
    // authentication that is stored in memory. UserDetails-based authentication is used by Spring Security when it is
    // configured to accept a username and password for authentication. Mainly intended for testing and demonstration
    // purposes, where a full-blown persistent system isn't required.
    @Bean
    public InMemoryUserDetailsManager setupUserDetailsManager() {
        UserDetails john = User
            .builder()
            .username("john")
            .password("{noop}test123")
            .roles(EMPLOYEE.getValue())
            .build();

        UserDetails mary = User
            .builder()
            .username("mary")
            .password("{noop}test123")
            .roles(EMPLOYEE.getValue(), MANAGER.getValue())
            .build();

        UserDetails susan = User
            .builder()
            .username("susan")
            .password("{noop}test123")
            .roles(EMPLOYEE.getValue(), MANAGER.getValue(), ADMIN.getValue())
            .build();

        return new InMemoryUserDetailsManager(john, mary, susan);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(configurer ->
            configurer
                .requestMatchers(HttpMethod.GET, "/api/employees").hasRole(EMPLOYEE.getValue())
                .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole(EMPLOYEE.getValue())
                .requestMatchers(HttpMethod.POST, "/api/employees").hasRole(MANAGER.getValue())
                .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole(MANAGER.getValue())
                .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole(ADMIN.getValue())
        );

        // Use HTTP basic authentication
        httpSecurity.httpBasic(Customizer.withDefaults());

        // Disable Cross Site Request Forgery (CSRF) => For web applications with HTML forms. It's not required for REST
        httpSecurity.csrf(csrf -> csrf.disable());

        return httpSecurity.build();
    }
}
