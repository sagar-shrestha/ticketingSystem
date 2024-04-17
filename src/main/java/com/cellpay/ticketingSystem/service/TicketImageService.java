package com.cellpay.ticketingSystem.service;

import com.cellpay.ticketingSystem.entity.TicketImage;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface TicketImageService {

    void saveTicketImage(TicketImage ticketImage);

    void getTicketImageById(int imageId, HttpServletResponse httpServletResponse) throws IOException;
}
