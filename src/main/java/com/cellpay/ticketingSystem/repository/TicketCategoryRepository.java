package com.cellpay.ticketingSystem.repository;

import com.cellpay.ticketingSystem.entity.TicketCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketCategoryRepository extends JpaRepository<TicketCategory, Integer> {
}
