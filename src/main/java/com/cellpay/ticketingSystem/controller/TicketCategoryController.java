package com.cellpay.ticketingSystem.controller;


import com.cellpay.ticketingSystem.common.pojo.response.GlobalApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class TicketCategoryController {


    public ResponseEntity<GlobalApiResponse> saveTicketCategory() {
        return ResponseEntity.ok(GlobalApiResponse.builder().build());
    }
}
