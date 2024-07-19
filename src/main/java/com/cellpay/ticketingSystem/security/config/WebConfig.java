package com.cellpay.ticketingSystem.security.config;

import com.cellpay.ticketingSystem.security.service.CorsConfigService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final CorsConfigService corsConfigService;

    public WebConfig(CorsConfigService corsConfigService) {
        this.corsConfigService = corsConfigService;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        corsConfigService.getAllCorsConfigs().forEach(corsConfig -> {
            registry.addMapping(corsConfig.getPathPattern())
                    .allowedOrigins(corsConfig.getAllowedOrigins().split(","))
                    .allowedMethods(corsConfig.getAllowedMethods().split(","))
                    .allowedHeaders(corsConfig.getAllowedHeaders().split(","))
                    .allowCredentials(corsConfig.isAllowCredentials())
                    .maxAge(corsConfig.getMaxAge());
        });
    }
}