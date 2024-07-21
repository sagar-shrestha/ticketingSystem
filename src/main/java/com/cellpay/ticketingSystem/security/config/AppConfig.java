package com.cellpay.ticketingSystem.security.config;

import com.cellpay.ticketingSystem.security.entity.CorsConfig;
import com.cellpay.ticketingSystem.security.repository.CorsConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
    private final CorsConfigRepository corsConfigRepository;

    public AppConfig(CorsConfigRepository corsConfigRepository) {
        this.corsConfigRepository = corsConfigRepository;
    }

    @Bean
    @Scope("singleton")
    public CorsConfig corsConfig() {
        CorsConfig corsConfigs = new CorsConfig();
        corsConfigs.setPathPattern("/**");
        corsConfigs.setAllowedOrigins("*");
        corsConfigs.setAllowedMethods("GET,POST,PUT,DELETE");
        corsConfigs.setAllowedHeaders("*");
        corsConfigs.setAllowCredentials(true);
        corsConfigs.setMaxAge(3600);
        return corsConfigRepository.save(corsConfigs);

    }
}
