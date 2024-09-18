package com.cellpay.ticketingSystem.repository;


import com.cellpay.ticketingSystem.entity.TicketTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TicketTopicRepository extends JpaRepository<TicketTopic, Integer> {


    @Query(value = "SELECT * FROM ticket_topic WHERE ", nativeQuery = true)
    List<TicketTopic> getAllTopicWithCategories();
}
