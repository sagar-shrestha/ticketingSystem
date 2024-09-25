package com.cellpay.ticketingSystem.helper;

import com.cellpay.ticketingSystem.common.pojo.response.TicketResponse;
import com.cellpay.ticketingSystem.entity.PaynetUserDetails;
import com.cellpay.ticketingSystem.entity.Ticket;
import com.cellpay.ticketingSystem.repository.PaynetUserDeatilsRepository;
import com.cellpay.ticketingSystem.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TicketHelper {

    private final TicketRepository ticketRepository;
    private final PaynetUserDeatilsRepository paynetUserDeatilsRepository;

    public TicketResponse getTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Ticket Not Found."));
        return TicketResponse.builder()
                .id(id)
                .ticketCategory(ticket.getTicketCategories().getFirst())
                .description(ticket.getDescription())
                .imageId(ticketRepository.getTicketById(id))
                .build();
    }

    public List<TicketResponse> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream()
                .map(ticket -> TicketResponse.builder()
                        .id(ticket.getId())
                        .ticketCategory(ticket.getTicketCategories().getFirst())
                        .description(ticket.getDescription())
                        .imageId(ticketRepository.getTicketById(ticket.getId()))
                        .build())
                .collect(Collectors.toList());
    }

    public Page<TicketResponse> getAllTicketsByUsernameWithPagination(String username, Pageable pageable) {
        PaynetUserDetails paynetUserDetails = paynetUserDeatilsRepository.getPaynetUserDetailsByUsername(username);
        List<Ticket> tickets = ticketRepository.getTicketsByPaynetUserDetails(paynetUserDetails);
        List<TicketResponse> ticketResponses = tickets.stream()
                .map(ticket -> TicketResponse.builder()
                        .id(ticket.getId())
                        .ticketCategory(ticket.getTicketCategories().getFirst())
                        .description(ticket.getDescription())
                        .imageId(ticketRepository.getTicketById(ticket.getId()))
                        .paynetUserDetails(ticket.getPaynetUserDetails())
                        .build())
                .toList();
        return new PageImpl<>(ticketResponses, pageable, ticketResponses.size());
    }

    public List<TicketResponse> getAllTicketsByUsernameWithoutPagination(String username) {
        PaynetUserDetails paynetUserDetails = paynetUserDeatilsRepository.getPaynetUserDetailsByUsername(username);
        List<Ticket> tickets = ticketRepository.getTicketsByPaynetUserDetails(paynetUserDetails);
        TicketResponse ticketResponse;
        List<TicketResponse> ticketResponses = new ArrayList<>();

        for (Ticket ticket : tickets) {
            ticketResponse = TicketResponse.builder()
                    .id(ticket.getId())
                    .ticketCategory(ticket.getTicketCategories().getFirst())
                    .description(ticket.getDescription())
                    .imageId(ticketRepository.getTicketById(ticket.getId()))
                    .ticketTopic(ticket.getTicketCategories().getFirst().getTicketTopic().getFirst())
                    .paynetUserDetails(ticket.getPaynetUserDetails())
                    .build();
            ticketResponses.add(ticketResponse);
        }
        return ticketResponses;
    }
}

