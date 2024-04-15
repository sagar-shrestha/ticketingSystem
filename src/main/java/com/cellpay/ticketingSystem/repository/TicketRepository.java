package com.cellpay.ticketingSystem.repository;

import com.cellpay.ticketingSystem.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
