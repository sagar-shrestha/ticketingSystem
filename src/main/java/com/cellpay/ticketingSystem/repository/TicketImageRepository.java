package com.cellpay.ticketingSystem.repository;

import com.cellpay.ticketingSystem.entity.TicketImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketImageRepository extends JpaRepository<TicketImage, Integer> {
}
