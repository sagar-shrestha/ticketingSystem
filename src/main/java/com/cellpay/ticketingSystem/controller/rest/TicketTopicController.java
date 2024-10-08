package com.cellpay.ticketingSystem.controller.rest;

import com.cellpay.ticketingSystem.common.annotations.CustomRestController;
import com.cellpay.ticketingSystem.common.pojo.request.TicketTopicRequest;
import com.cellpay.ticketingSystem.common.pojo.response.GlobalApiResponse;
import com.cellpay.ticketingSystem.service.TicketTopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



@CustomRestController
@RequiredArgsConstructor
public class TicketTopicController {

    private final TicketTopicService ticketTopicService;

    @GetMapping("getAllTopic")
    public ResponseEntity<GlobalApiResponse> getAllTopicWithoutPagination() {
        return ResponseEntity.ok(GlobalApiResponse
                .builder()
                .code(HttpStatus.OK.value())
                .data(ticketTopicService.getAllTopicWithoutPagination())
                .message("Topic Found Successfully.")
                .status(true)
                .build());
    }
}
