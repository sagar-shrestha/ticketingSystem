package com.cellpay.ticketingSystem.repository;

import com.cellpay.ticketingSystem.entity.TicketPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketPhotoRepository extends JpaRepository<TicketPhoto, Integer> {
}
