package com.cellpay.ticketingSystem.controller;

import com.cellpay.ticketingSystem.common.pojo.response.GlobalApiResponse;
import com.cellpay.ticketingSystem.common.util.GenericFileUtil;
import com.cellpay.ticketingSystem.entity.TicketImage;
import com.cellpay.ticketingSystem.repository.TicketImageRepository;
import com.cellpay.ticketingSystem.service.TicketImageService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.MalformedURLException;

@RestController
@RequestMapping("rest/")
@RequiredArgsConstructor
public class TicketImageController {

    private final TicketImageService ticketImageService;
    private final GenericFileUtil genericFileUtil;
    private final TicketImageRepository ticketImageRepository;


    @GetMapping(value = "getImageById/{id}", produces = MediaType.MULTIPART_MIXED_VALUE)
    public ResponseEntity<?> getTicketImageById(@PathVariable("id") int id,
                                                HttpServletResponse httpServletResponse) throws IOException {
        TicketImage ticketImage = ticketImageRepository.findById(id)
                .orElseThrow(() -> new MalformedURLException("Image not found"));
        genericFileUtil.getFileAsResource(ticketImage.getImage(), httpServletResponse);
//        String contentType = "image/jpeg";
//        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(contentType))
//                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
//                .body(resource);
        return ResponseEntity.ok(GlobalApiResponse
                .builder()
                .code(HttpStatus.OK.value())
                .data(null)
                .message("Ticket Saved Successfully.")
                .build());


    }

    @GetMapping(value = "getImageByIdAsImage/{id}", produces = MediaType.MULTIPART_MIXED_VALUE)
    public ResponseEntity<?> getTicketImageByIdAsImage(@PathVariable("id") int id,
                                                       HttpServletResponse httpServletResponse) throws IOException {
        TicketImage ticketImage = ticketImageRepository.findById(id)
                .orElseThrow(() -> new MalformedURLException("Image not found"));
        byte[] imageData = ticketImage.getImage().getBytes();
        return null;


    }
}
