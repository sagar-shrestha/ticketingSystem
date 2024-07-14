package com.cellpay.ticketingSystem.repository;

import com.cellpay.ticketingSystem.entity.TicketCategory;
import com.cellpay.ticketingSystem.entity.TicketTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketCategoryRepository extends JpaRepository<TicketCategory, Integer> {
    List<TicketCategory>  findByTicketTopic(TicketTopic ticketTopic);
}
