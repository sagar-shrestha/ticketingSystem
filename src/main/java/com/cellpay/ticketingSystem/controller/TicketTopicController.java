package com.cellpay.ticketingSystem.controller;

import com.cellpay.ticketingSystem.common.pojo.request.TicketTopicRequest;
import com.cellpay.ticketingSystem.common.pojo.response.GlobalApiResponse;
import com.cellpay.ticketingSystem.service.TicketTopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class TicketTopicController {

    private final TicketTopicService ticketTopicService;

    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
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

    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
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

    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
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

    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @GetMapping("getAllTopic")
    public ResponseEntity<GlobalApiResponse> getAllTopic(@RequestParam("pageNo") int pageNo,
                                                         @RequestParam("pageSize") int pageSize) {
        return ResponseEntity.ok(GlobalApiResponse
                .builder()
                .code(HttpStatus.OK.value())
                .data(ticketTopicService.getAllTopic(pageNo, pageSize))
                .message("Topic Found Successfully.")
                .status(true)
                .build());
    }
}
