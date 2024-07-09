package com.cellpay.ticketingSystem.repository;

import com.cellpay.ticketingSystem.entity.Ticket;
import com.cellpay.ticketingSystem.entity.TicketCategory;
import com.cellpay.ticketingSystem.entity.TicketTopic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketTopicRepository extends JpaRepository<TicketTopic, Integer> {


}
