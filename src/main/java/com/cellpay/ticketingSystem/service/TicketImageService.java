package com.cellpay.ticketingSystem.service;

import com.cellpay.ticketingSystem.entity.TicketImage;
import jakarta.servlet.http.HttpServletResponse;

import java.net.MalformedURLException;

public interface TicketImageService {

    void saveTicketImage(TicketImage ticketImage);

    void getTicketImageById(int imageId, HttpServletResponse httpServletResponse) throws MalformedURLException;
}
