//package com.cellpay.ticketingSystem.security.config;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@RequiredArgsConstructor
//@EnableWebSecurity
//@EnableMethodSecurity
//public class WebSecurityConfig {
//
//    @Bean
//    @Order(1)
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(request -> {
//                    request.requestMatchers("/ticket/**").permitAll();
//                    request.requestMatchers("/super/**").permitAll();
//                    request.requestMatchers("/rest/super").permitAll();
//                    request.requestMatchers("/assets/**").permitAll();
//                    request.requestMatchers("/layout_fragments/**").permitAll();
//                    request.requestMatchers("/error").permitAll();
//                    request.requestMatchers("/web/**").hasAnyRole("SUPER_ADMIN", "ADMIN");
//                    request.requestMatchers("/rest/**").hasAnyRole("SUPER_ADMIN", "ADMIN");
//                    request.anyRequest().authenticated();
//                })
//                .formLogin(httpSecurityFormLoginConfigurer -> {
//                    httpSecurityFormLoginConfigurer
//                            .loginPage("/login")
//                            .loginProcessingUrl("/ticket/login")
//                            //  .defaultSuccessUrl("/web/home")
//                            .permitAll();
//                })
//                .build();
//    }
//
//
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
//            throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//}
