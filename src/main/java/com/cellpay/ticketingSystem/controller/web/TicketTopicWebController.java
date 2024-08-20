package com.cellpay.ticketingSystem.controller.web;

import com.cellpay.ticketingSystem.common.annotations.CustomWebController;
import com.cellpay.ticketingSystem.common.pojo.request.TicketTopicRequest;
import com.cellpay.ticketingSystem.entity.TicketTopic;
import com.cellpay.ticketingSystem.service.TicketTopicService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
        return "redirect:/web/getAllTopic";
        
    }


    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @PostMapping("/updateTopic")
    public String updateTicketTopic(@RequestParam int id, @ModelAttribute TicketTopicRequest ticketTopicRequest,
                                    Model model) {
        TicketTopic ticketTopic = ticketTopicService.updateTicketTopic(ticketTopicRequest, id);
        model.addAttribute("ticketTopic", ticketTopic);
        model.addAttribute("message", "Topic Updated Successfully.");
        return "redirect:/web/getAllTopic";
    }


    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @GetMapping("/updateTopicById/{id}")
    public String updateTopicById(@PathVariable int id, Model model) {
        TicketTopic ticketTopic = (TicketTopic) ticketTopicService.getTopicById(id);
        model.addAttribute("ticketTopic", ticketTopic);
        return "/ticket-topic/ticket-topic-edit";
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @GetMapping("/getTopicById/{id}")
    public String getTopicById(@PathVariable int id, Model model) {
        TicketTopic ticketTopic = (TicketTopic) ticketTopicService.getTopicById(id);
        model.addAttribute("ticketTopic", ticketTopic);
        return "/ticket-topic/ticket-topic-details";
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @GetMapping("getAllTopic")
    public String getAllTopic(@RequestParam(defaultValue = "1") int pageNumber,
                              @RequestParam(defaultValue = "10") int pageSize, Model model) {
        Page<TicketTopic> ticketTopics = ticketTopicService.getAllTopicWithPagination(pageNumber, pageSize);
        pageNumber = ticketTopics.getNumber();
        pageSize = ticketTopics.getSize();
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
