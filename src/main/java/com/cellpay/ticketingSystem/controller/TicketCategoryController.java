package com.cellpay.ticketingSystem.controller;


import com.cellpay.ticketingSystem.common.pojo.request.TicketCategoryRequest;
import com.cellpay.ticketingSystem.common.pojo.response.GlobalApiResponse;
import com.cellpay.ticketingSystem.service.TicketCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class TicketCategoryController {

    private final TicketCategoryService ticketCategoryService;

    @PostMapping("saveTicketCategory")
    public ResponseEntity<GlobalApiResponse> saveTicketCategory(@RequestBody TicketCategoryRequest
                                                                        ticketCategoryRequest) {
        ticketCategoryService.saveTicketCategory(ticketCategoryRequest);
        return ResponseEntity.ok(GlobalApiResponse
                .builder()
                .code(HttpStatus.CREATED.value())
                .data(null)
                .status(true)
                .message("TicketCategory saved successfully")
                .build());
    }
}
