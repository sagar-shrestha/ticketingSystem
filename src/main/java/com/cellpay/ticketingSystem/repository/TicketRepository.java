package com.cellpay.ticketingSystem.repository;

import com.cellpay.ticketingSystem.common.pojo.response.TicketResponse;
import com.cellpay.ticketingSystem.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

//    @Query("SELECT * FROM ticketing_system.ticket_image tp \n" +
//            "INNER JOIN ticketing_system.ticket t ON tp.ticket_id = t.id ")
//    TicketResponse getTicketById(Long id);
}
