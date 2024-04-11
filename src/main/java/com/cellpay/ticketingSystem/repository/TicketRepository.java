package com.cellpay.ticketingSystem.repository;

import com.cellpay.ticketingSystem.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {
}
