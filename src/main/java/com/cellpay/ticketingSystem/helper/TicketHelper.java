package com.cellpay.ticketingSystem.helper;

import com.cellpay.ticketingSystem.common.pojo.response.TicketResponse;
import com.cellpay.ticketingSystem.entity.Ticket;
import com.cellpay.ticketingSystem.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketHelper {

    private final TicketRepository ticketRepository;

    public TicketResponse getTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Ticket Not Found."));
        return TicketResponse.builder()
                .id(id)
                .ticketCategory(ticket.getTicketCategory())
                .description(ticket.getDescription())
                .imageId(ticketRepository.getTicketById(id))
                .build();
    }
}
