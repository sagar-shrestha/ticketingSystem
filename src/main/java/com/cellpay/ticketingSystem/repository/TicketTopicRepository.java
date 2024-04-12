package com.cellpay.ticketingSystem.repository;

import com.cellpay.ticketingSystem.entity.TicketTopic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketTopicRepository extends JpaRepository<TicketTopic, Integer> {
}
