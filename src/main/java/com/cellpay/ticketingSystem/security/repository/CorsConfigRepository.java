package com.cellpay.ticketingSystem.security.repository;

import com.cellpay.ticketingSystem.security.entity.CorsConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorsConfigRepository extends JpaRepository<CorsConfig, Long> {

}
