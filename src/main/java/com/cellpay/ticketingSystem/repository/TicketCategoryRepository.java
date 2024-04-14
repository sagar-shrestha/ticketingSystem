package com.cellpay.ticketingSystem.repository;

import com.cellpay.ticketingSystem.entity.TicketCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketCategoryRepository extends JpaRepository<TicketCategory, Integer> {
}
