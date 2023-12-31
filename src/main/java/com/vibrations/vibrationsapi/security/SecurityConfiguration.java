package com.vibrations.vibrationsapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.cors.CorsConfiguration;

/**
 * Spring Boot Security Configurations. Configures allowed CORs settings,
 * Disables CSRF access, determines which endpoints require authnetication,
 * and which ones don't
 */
@Configuration
@EnableWebSecurity(debug = false)
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowCredentials(true);
                    config.addAllowedOrigin("http://localhost:3000"); // Local development
                    config.addAllowedOrigin(
                            "http://dev-vib-ui-final-eb-env.eba-xvyd3ppt.us-east-2.elasticbeanstalk.com/"); // Cloud development host
                    config.addAllowedHeader("Authorization");
                    config.addAllowedHeader("Content-Type");
                    config.addAllowedMethod("GET");
                    config.addAllowedMethod("POST");
                    config.addAllowedMethod("OPTIONS");
                    return config;
                })).csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authz) -> authz.requestMatchers(
                         "/api/users/register","/api/users/login", "/hello", "/hello/friend")
                        .permitAll().anyRequest().authenticated())
                .sessionManagement((sessionManagement) -> {
                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                }).exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint((request, response, ex) -> {
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
                        }))
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()))
                .logout(logout -> logout
                        .logoutUrl("/api/logout"));
        return http.build();
    }
}
