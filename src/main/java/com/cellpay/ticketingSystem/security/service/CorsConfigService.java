package com.cellpay.ticketingSystem.security.service;

import com.cellpay.ticketingSystem.security.entity.CorsConfig;
import com.cellpay.ticketingSystem.security.repository.CorsConfigRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorsConfigService {
    private final CorsConfigRepository configRepository;

    public CorsConfigService(CorsConfigRepository configRepository) {
        this.configRepository = configRepository;
    }
    public List<CorsConfig> getAllCorsConfigs() {
        return configRepository.findAll();
    }
}
