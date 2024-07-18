package com.cellpay.ticketingSystem.controller.rest;

import com.cellpay.ticketingSystem.common.annotations.CustomRestController;
import com.cellpay.ticketingSystem.common.pojo.request.TicketRequest;
import com.cellpay.ticketingSystem.common.pojo.response.GlobalApiResponse;
import com.cellpay.ticketingSystem.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;

@CustomRestController
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping(value = "/saveTicket", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<GlobalApiResponse> saveTicket(@ModelAttribute TicketRequest ticketRequestPojo) throws Exception {
        ticketService.saveTicket(ticketRequestPojo);
        return ResponseEntity.ok(GlobalApiResponse
                .builder()
                .code(HttpStatus.CREATED.value())
                .data(null)
                .message("Ticket Saved Successfully.")
                .status(true)
                .build());
    }


    @GetMapping(value = "/getTicketByUsername")
    public ResponseEntity<GlobalApiResponse> getTicketByUsername(@RequestParam String username ) throws Exception {
        return ResponseEntity.ok(GlobalApiResponse
                .builder()
                .code(HttpStatus.OK.value())
                .data(ticketService.getAllTicketsByUsernameWithoutPagination(username))
                .message("Topic Found Successfully.")
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
