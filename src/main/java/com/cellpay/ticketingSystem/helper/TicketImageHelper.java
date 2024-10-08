package com.cellpay.ticketingSystem.helper;

import com.cellpay.ticketingSystem.common.util.GenericFileUtil;
import com.cellpay.ticketingSystem.entity.TicketImage;
import com.cellpay.ticketingSystem.repository.TicketImageRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.MalformedURLException;

@Component
@RequiredArgsConstructor
public class TicketImageHelper {

    private final TicketImageRepository ticketImageRepository;
    private final GenericFileUtil genericFileUtil;

    public void getTicketImageById(int imageId, HttpServletResponse httpServletResponse) throws IOException {
        System.out.println("Fetching image with ID: " + imageId);
        TicketImage ticketImage = ticketImageRepository.findById(imageId)
                .orElseThrow(() -> new MalformedURLException("Image not found"));
        System.out.println("Image found: " + ticketImage.getImage());
        genericFileUtil.getFileAsResource(ticketImage.getImage(), httpServletResponse);
    }
}
