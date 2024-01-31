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
        // Tell Spring Security to use JDBC authentication with our data source
        // Spring Security will use predefined tables schema => Users & Roles tables
        // No longer hard-coding users
        // Spring will query db for each login, no need to restart the app
        return new JdbcUserDetailsManager(dataSource);
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
