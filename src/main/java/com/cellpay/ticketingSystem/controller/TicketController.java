package com.cellpay.ticketingSystem.controller;

import com.cellpay.ticketingSystem.common.pojo.request.TicketRequest;
import com.cellpay.ticketingSystem.common.pojo.response.GlobalApiResponse;
import com.cellpay.ticketingSystem.repository.TicketRepository;
import com.cellpay.ticketingSystem.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;

@RestController
@RequestMapping("rest/")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping(value = "saveTicket", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    private ResponseEntity<GlobalApiResponse> saveTicket(@ModelAttribute TicketRequest ticketRequestPojo) throws Exception {
        ticketService.saveTicket(ticketRequestPojo);
        return ResponseEntity.ok(GlobalApiResponse
                .builder()
                .code(HttpStatus.CREATED.value())
                .data(null)
                .message("Ticket Saved Successfully.")
                .status(true)
                .build());
    }

    @PutMapping("saveTicket/{id}")
    public ResponseEntity<GlobalApiResponse> updateTicket(@ModelAttribute TicketRequest ticketRequestPojo, @PathVariable Long id) throws Exception {
        ticketService.updateTicket(ticketRequestPojo, id);
        return ResponseEntity.ok(GlobalApiResponse
                .builder()
                .code(HttpStatus.OK.value())
                .data(null)
                .message("Ticket Updated Successfully.")
                .status(true)
                .build());
    }

    @GetMapping("getTicketById/{id}")
    public ResponseEntity<GlobalApiResponse> getTicketById(@PathVariable Long id) throws MalformedURLException {
        return ResponseEntity.ok(GlobalApiResponse
                .builder()
                .code(HttpStatus.OK.value())
                .data(ticketService.getTicketById(id))
                .message("Ticket Found Successfully.")
                .status(true)
                .build());
    }
}
