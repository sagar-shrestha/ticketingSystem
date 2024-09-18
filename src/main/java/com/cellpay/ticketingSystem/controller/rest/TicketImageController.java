package com.cellpay.ticketingSystem.controller.rest;

import com.cellpay.ticketingSystem.common.annotations.CustomRestController;
import com.cellpay.ticketingSystem.common.pojo.response.GlobalApiResponse;
import com.cellpay.ticketingSystem.service.TicketImageService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

@CustomRestController
@RequiredArgsConstructor
public class TicketImageController {

    private final TicketImageService ticketImageService;

    @GetMapping(value = "getImageById/{id}", produces = MediaType.MULTIPART_MIXED_VALUE)
    public ResponseEntity<?> getTicketImageById(@PathVariable("id") int imageId,
                                                HttpServletResponse httpServletResponse) throws IOException {
        ticketImageService.getTicketImageById(imageId, httpServletResponse);
        return ResponseEntity.ok(GlobalApiResponse
                .builder()
                .code(HttpStatus.OK.value())
                .data(null)
                .message("Image found Successfully.")
                .build());
    }
}
