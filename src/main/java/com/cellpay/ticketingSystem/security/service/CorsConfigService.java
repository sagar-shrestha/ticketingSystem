package com.cellpay.ticketingSystem.security.service;

import com.cellpay.ticketingSystem.security.entity.CorsConfig;
import org.springframework.stereotype.Service;

@Service
public class CorsConfigService {
    private final CorsConfig corsConfig;

    public CorsConfigService(CorsConfig corsConfig) {
        this.corsConfig = corsConfig;
    }
    public void printCorsConfig() {
        System.out.println("Allowed Origins: " + corsConfig.getAllowedOrigins());
    }
}
