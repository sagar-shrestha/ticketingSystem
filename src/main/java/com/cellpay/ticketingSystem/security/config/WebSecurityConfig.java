package com.cellpay.ticketingSystem.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
//@EnableMethodSecurity
@Order(2)
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(AbstractHttpConfigurer::disable).csrf(AbstractHttpConfigurer::disable)
                .securityMatcher("/ticket/**", "/web/**", "/logout")
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/ticket/**").permitAll()
                        .requestMatchers("/super/**").permitAll()
                        .requestMatchers("/rest/super").permitAll()
                        .requestMatchers("/assets/**").permitAll()
                        .requestMatchers("/layout_fragments/**").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/web/**").hasAnyRole("SUPER_ADMIN", "ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/ticket/login")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
