package com.cellpay.ticketingSystem.controller;

import com.cellpay.ticketingSystem.common.pojo.request.TicketTopicRequest;
import com.cellpay.ticketingSystem.common.pojo.response.GlobalApiResponse;
import com.cellpay.ticketingSystem.service.TicketTopicService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class TicketTopicController {

    private final TicketTopicService ticketTopicService;

    @PostMapping("/ticketTopic")
    public ResponseEntity<GlobalApiResponse> saveTicketTopic(
            @RequestBody TicketTopicRequest ticketTopicRequest) {
        ticketTopicService.saveTicketTopic(ticketTopicRequest);
        return ResponseEntity.ok(GlobalApiResponse.builder()
                .code(HttpStatus.CREATED.value())
                .data(null)
                .status(true)
                .message("Topic Saved Successfully.")
                .build());
    }
}
