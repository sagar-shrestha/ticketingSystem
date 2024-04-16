package com.cellpay.ticketingSystem.controller;


import com.cellpay.ticketingSystem.common.pojo.request.TicketCategoryRequest;
import com.cellpay.ticketingSystem.common.pojo.response.GlobalApiResponse;
import com.cellpay.ticketingSystem.service.TicketCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                .message("TicketCategory saved successfully")
                .status(true)
                .build());
    }

    @PutMapping("/saveTicketCategory/{id}")
    public ResponseEntity<GlobalApiResponse> updateTicketCategory(@RequestBody TicketCategoryRequest ticketCategoryRequest,
                                                                  @PathVariable int id) {
        return ResponseEntity.ok(GlobalApiResponse
                .builder()
                .code(HttpStatus.OK.value())
                .data(ticketCategoryService.updateTicketCategory(ticketCategoryRequest, id))
                .message("TicketCategory updated successfully")
                .status(true)
                .build());
    }

    @GetMapping("getCategoryById/{id}")
    public ResponseEntity<GlobalApiResponse> getCategoryById(@PathVariable int id) {
        return ResponseEntity.ok(GlobalApiResponse
                .builder()
                .code(HttpStatus.OK.value())
                .data(ticketCategoryService.getCategoryById(id))
                .message("Ticket Category Found Successfully.")
                .status(true)
                .build());
    }

    @GetMapping("getAllCategory")
    public ResponseEntity<GlobalApiResponse> getAllCategory(@RequestParam("pageNo") int pageNo,
                                                            @RequestParam("pageSize") int pageSize) {
        return ResponseEntity.ok(GlobalApiResponse
                .builder()
                .code(HttpStatus.OK.value())
                .data(ticketCategoryService.getAllCategory(pageNo, pageSize))
                .message("Ticket Category Found Successfully.")
                .status(true)
                .build());
    }
}
