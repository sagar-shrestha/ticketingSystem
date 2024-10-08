package com.cellpay.ticketingSystem.controller.web;

import com.cellpay.ticketingSystem.common.annotations.CustomWebController;
import com.cellpay.ticketingSystem.common.pojo.request.TicketRequest;
import com.cellpay.ticketingSystem.common.pojo.response.TicketResponse;
import com.cellpay.ticketingSystem.entity.TicketCategory;
import com.cellpay.ticketingSystem.entity.TicketTopic;
import com.cellpay.ticketingSystem.service.TicketCategoryService;
import com.cellpay.ticketingSystem.service.TicketService;
import com.cellpay.ticketingSystem.service.TicketTopicService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CustomWebController
@RequiredArgsConstructor
public class TicketWebController {


    private final TicketService ticketService;
    private final TicketTopicService ticketTopicService;
    private  final TicketCategoryService ticketCategoryService;



    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @GetMapping("/AddTicketing")
    public String ticketing(Model model) {
        List<TicketTopic> ticketTopics = ticketTopicService.getAllTopicWithoutPagination();
        List<TicketCategory> ticketCategories = ticketCategoryService.getAllCategoryWithOutPagination();
        model.addAttribute("ticketRequestPojo", new TicketRequest());
        model.addAttribute("ticketTopics", ticketTopics);
        model.addAttribute("ticketCategories", ticketCategories);

        return "/Ticket/ticketing";
    }



    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @PostMapping("/saveTicketing")
    public String saveTicket(@ModelAttribute TicketRequest ticketRequestPojo, Model model, HttpSession session) throws Exception {
        try {
            ticketService.saveTicket(ticketRequestPojo);
            session.setAttribute("sessionMessage", "Ticket saved successfully.");
        } catch (Exception e) {
            session.setAttribute("sessionMessage", "Cannot save ticket.");
            e.printStackTrace(); // Log the exception stack trace
        }
        model.addAttribute("ticketRequestPojo", ticketRequestPojo);
        return "Ticket/ticketing";
    }


    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @GetMapping("/ListTicketing")
    public String getTickets(Model model) {
        List<TicketResponse> tickets = ticketService.getAllTickets();
        model.addAttribute("tickets", tickets);
        return "Ticket/ticketing-list";
    }


}
