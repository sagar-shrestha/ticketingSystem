package com.cellpay.ticketingSystem.service;


import com.cellpay.ticketingSystem.Exception.DataNotFoundException;
import com.cellpay.ticketingSystem.entity.TicketImage;
import com.cellpay.ticketingSystem.helper.TicketImageHelper;
import com.cellpay.ticketingSystem.repository.TicketImageRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;


@Service
@RequiredArgsConstructor
public class TicketImageServiceImpl implements TicketImageService {

    private final TicketImageRepository ticketImageRepository;
    private final TicketImageHelper ticketImageHelper;

    @Override
    public void saveTicketImage(TicketImage ticketImage) {
        try {
            ticketImageRepository.save(ticketImage);
        }
        catch (Exception e) {
            throw new DataNotFoundException("Ticket image save failed");
        }
    }

    @Override
    public void getTicketImageById(int imageId, HttpServletResponse httpServletResponse) throws IOException {
        try{
        ticketImageHelper.getTicketImageById(imageId, httpServletResponse);
    }
        catch(MalformedURLException e){
            throw new DataNotFoundException("Image not found.");
        }
    }

}
