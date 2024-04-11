package com.cellpay.ticketingSystem.service;

import com.cellpay.ticketingSystem.common.pojo.request.TicketRequestPojo;

import java.io.IOException;

public interface TicketService {

    void saveTicket(TicketRequestPojo ticketRequestPojo) throws Exception;
}
