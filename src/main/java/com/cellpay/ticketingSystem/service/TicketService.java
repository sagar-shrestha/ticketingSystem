package com.cellpay.ticketingSystem.service;

import com.cellpay.ticketingSystem.common.pojo.request.TicketRequest;

public interface TicketService {

    void saveTicket(TicketRequest ticketRequestPojo) throws Exception;
}
