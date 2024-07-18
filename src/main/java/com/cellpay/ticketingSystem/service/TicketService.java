package com.cellpay.ticketingSystem.service;

import com.cellpay.ticketingSystem.common.pojo.request.TicketRequest;
import com.cellpay.ticketingSystem.common.pojo.response.TicketResponse;
import com.cellpay.ticketingSystem.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.expression.spel.SpelEvaluationException;

import java.net.MalformedURLException;
import java.util.List;

public interface TicketService {

    boolean saveTicket(TicketRequest ticketRequestPojo) throws Exception;

    void updateTicket(TicketRequest ticketRequestPojo, Long id) throws Exception;

    TicketResponse getTicketById(Long id) throws MalformedURLException;

    List<TicketResponse> getAllTickets();

    Page<TicketResponse> getAllTicketsByUsernameWithPagination(String username, Integer pageNumber, Integer pageSize);

    TicketResponse getDeleteById(Long id) throws MalformedURLException;
    Page<TicketResponse> getAllTicketsByUsernameWithoutPagination(String username);

}
