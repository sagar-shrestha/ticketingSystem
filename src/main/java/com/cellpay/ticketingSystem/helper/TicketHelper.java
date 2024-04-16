package com.cellpay.ticketingSystem.helper;

import com.cellpay.ticketingSystem.common.pojo.response.TicketResponse;
import com.cellpay.ticketingSystem.common.util.GenericFileUtil;
import com.cellpay.ticketingSystem.entity.Ticket;
import com.cellpay.ticketingSystem.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketHelper {

    private final GenericFileUtil genericFileUtil;
    private final TicketRepository ticketRepository;


    public TicketResponse getTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Ticket Not Found."));
//        for (TicketImage ticketPhoto : ticket.getImage()) {
//            Resource photo = genericFileUtil.getFile(ticketPhoto.getImage());
//            resourceList.add(photo);
//            photoIds.add(ticketPhoto.getId());
//        }

        return TicketResponse.builder()
                .id(ticket.getId())
                .ticketCategory(ticket.getTicketCategory())
                .description(ticket.getDescription())
                .build();
    }
}
