//package com.cellpay.ticketingSystem.security.config;
//
//import com.cellpay.ticketingSystem.security.repository.UserInfoRepository;
//import com.cellpay.ticketingSystem.security.service.CustomUserDetailService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@RequiredArgsConstructor
//@EnableWebSecurity
//@EnableMethodSecurity
//public class WebSecurityConfig {
//
//    private final CustomUserDetailService customUserDetailService;
//    private final JwtAuthFilter jwtAuthFilter;
//
//    @Bean
//    public UserDetailsService userDetailsService(UserInfoRepository userInfoRepository) {
//        return new CustomUserDetailService(userInfoRepository);
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider(UserInfoRepository userInfoRepository) {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService(userInfoRepository));
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//        return daoAuthenticationProvider;
//    }
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
//    @Bean
//    @Order(2)
//    public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http, UserInfoRepository userInfoRepository) throws Exception {
//        return http.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(request -> {
//                    request.requestMatchers("/super/authenticate").permitAll();
//                    request.requestMatchers("/rest/**").hasAnyRole("SUPER_ADMIN", "ADMIN");
//                    request.anyRequest().authenticated();
//                })
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authenticationProvider(authenticationProvider(userInfoRepository))
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)  // Ensure this line is present
//                .build();
//    }
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
