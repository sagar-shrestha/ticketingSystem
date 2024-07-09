package com.cellpay.ticketingSystem.repository;

import com.cellpay.ticketingSystem.entity.Ticket;
import com.cellpay.ticketingSystem.entity.TicketImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketImageRepository extends JpaRepository<TicketImage, Integer> {
    List<TicketImage> findAllByTicket(Ticket ticket);
}
