package com.cellpay.ticketingSystem.controller;

import com.cellpay.ticketingSystem.common.pojo.request.TicketTopicRequest;
import com.cellpay.ticketingSystem.common.pojo.response.GlobalApiResponse;
import com.cellpay.ticketingSystem.service.TicketTopicService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class TicketTopicController {

    private final TicketTopicService ticketTopicService;

    @PostMapping("/saveTopic")
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

    @PutMapping("/saveTopic/{id}")
    public ResponseEntity<GlobalApiResponse> updateTicketTopic(@RequestBody TicketTopicRequest ticketTopicRequest,
                                                              @PathVariable int id) {
        return ResponseEntity.ok(GlobalApiResponse.builder()
                .code(HttpStatus.OK.value())
                .data(ticketTopicService.updateTicketTopic(ticketTopicRequest, id))
                .message("Topic Updated Successfully.")
                .status(true)
                .build());
    }

    @GetMapping("/getTopicById/{id}")
    public ResponseEntity<GlobalApiResponse> getTopicById(@PathVariable int id) {
        return ResponseEntity.ok(GlobalApiResponse
                .builder()
                .code(HttpStatus.OK.value())
                .data(ticketTopicService.getTopicById(id))
                .message("Topic Found Successfully.")
                .status(true)
                .build());
    }

    @GetMapping("getAllTopic")
    public ResponseEntity<GlobalApiResponse> getAllTopic() {
        return ResponseEntity.ok(GlobalApiResponse
                .builder()
                .code(HttpStatus.OK.value())
                .data(ticketTopicService.getAllTopic())
                .message("Topic Found Successfully.")
                .status(true)
                .build());
    }
}
