package com.cellpay.ticketingSystem.controller.web;

import com.cellpay.ticketingSystem.common.annotations.CustomWebController;
import com.cellpay.ticketingSystem.common.pojo.request.TicketTopicRequest;
import com.cellpay.ticketingSystem.common.pojo.response.GlobalApiResponse;
import com.cellpay.ticketingSystem.entity.TicketTopic;
import com.cellpay.ticketingSystem.repository.TicketTopicRepository;
import com.cellpay.ticketingSystem.service.TicketTopicService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@CustomWebController
@RequiredArgsConstructor
public class TicketTopicWebController {

    private final TicketTopicService ticketTopicService;

    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @PostMapping("/saveTopic")
    public String saveTicketTopic(
            @ModelAttribute TicketTopicRequest ticketTopicRequest, Model model, HttpSession session) {
        if (ticketTopicService.saveTicketTopic(ticketTopicRequest)) {
            session.setAttribute("sessionMessage", "Topic saved successfully");
        } else {
            session.setAttribute("sessionMessage", "Cannot save ticket topic");
        }
        model.addAttribute("message", "Topic Saved Successfully.");
        return "redirect:/web/getAllTopic?pageNo=0&pageSize=10";
    }

//    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
//    @PutMapping("/saveTopic/{id}")
//    public ResponseEntity<GlobalApiResponse> updateTicketTopic(@RequestBody TicketTopicRequest ticketTopicRequest,
//                                                               @PathVariable int id) {
//        return ResponseEntity.ok(GlobalApiResponse.builder()
//                .code(HttpStatus.OK.value())
//                .data(ticketTopicService.updateTicketTopic(ticketTopicRequest, id))
//                .message("Topic Updated Successfully.")
//                .status(true)
//                .build());
//    }

//    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
//    @PutMapping("/updateTopic/{id}")
//    public String updateTicketTopic() {
//
//    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @PutMapping("/saveTopic/{id}")
    public String updateTicketTopic(@ModelAttribute TicketTopicRequest ticketTopicRequest,
                                                               @PathVariable int id, Model model) {
        TicketTopic ticketTopic = ticketTopicService.updateTicketTopic(ticketTopicRequest, id);
        model.addAttribute("ticketTopic", ticketTopic);
        model.addAttribute("message", "Topic Updated Successfully.");
//        return "redirect:/web/getTopicById/" + id;
        return "/ticket-topic/ticket-topic-edit";


    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @GetMapping("/getTopicById/{id}")
    public String getTopicById(@PathVariable int id, Model model) {
        TicketTopic ticketTopic = ticketTopicService.getTopicById(id);
        model.addAttribute("ticketTopic", ticketTopic);
        return "/ticket-topic/ticket-topic-details";
    }

//    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
//    @GetMapping("getAllTopic")
//    public String getAllTopic(@RequestParam("pageNo") int pageNo,
//                              @RequestParam("pageSize") int pageSize, Model model) {
//        Page<TicketTopic> ticketTopics = ticketTopicService.getAllTopic(pageNo, pageSize);
//        model.addAttribute("ticketTopics", ticketTopics);
//        return "/ticket-topic/ticket-topic-list";
//    }

    private final TicketTopicRepository ticketTopicRepository;

    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @GetMapping("getAllTopic")
    public String getAllTopic(Pageable pageable, Model model) {
        Page<TicketTopic> ticketTopics = ticketTopicRepository.findAll(pageable);
        int pageNumber = ticketTopics.getNumber();
        int pageSize = ticketTopics.getSize();
        int totalPages = ticketTopics.getTotalPages();
        model.addAttribute("ticketTopics", ticketTopics);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", totalPages);
        return "/ticket-topic/ticket-topic-list";
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @GetMapping("/ticketTopic")
    public String ticketManagement() {
        return "/ticket-topic/ticket-topic";
    }
}
