package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


import javax.sql.DataSource;

import static com.luv2code.springboot.cruddemo.security.Luv2codeRole.*;

// @Configuration is a class-level annotation indicating that an object is a source of bean definitions.
@Configuration
public class DemoSecurityConfig {

    // Inject DataSource autoconfigured by spring boot
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // If we use custom tables, we'll need to tell Spring Security how to find users and roles...
        // Question mark parameter will be the username from login
        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT user_id, pw, active FROM members WHERE user_id=?");

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT user_id, role FROM roles WHERE user_id=?");

        return jdbcUserDetailsManager;
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
