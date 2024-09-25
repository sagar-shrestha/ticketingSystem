package com.cellpay.ticketingSystem.controller.web;

import com.cellpay.ticketingSystem.common.annotations.CustomWebController;
import com.cellpay.ticketingSystem.common.pojo.request.TicketCategoryRequest;
import com.cellpay.ticketingSystem.common.pojo.request.TicketRequest;
import com.cellpay.ticketingSystem.common.pojo.response.TicketResponse;
import com.cellpay.ticketingSystem.entity.TicketCategory;
import com.cellpay.ticketingSystem.entity.TicketTopic;
import com.cellpay.ticketingSystem.security.entity.UserInfo;
import com.cellpay.ticketingSystem.service.TicketCategoryService;
import com.cellpay.ticketingSystem.service.TicketService;
import com.cellpay.ticketingSystem.service.TicketTopicService;
import com.cellpay.ticketingSystem.service.UserInfoService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.security.Principal;
import java.util.List;

@CustomWebController
@RequiredArgsConstructor
public class TicketWebController {


    private final TicketService ticketService;
    private final TicketTopicService ticketTopicService;
    private final TicketCategoryService ticketCategoryService;
    private final UserInfoService userInfoService;



    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @GetMapping("/saveTicketing")
    public String ticketing(Model model, Principal principal) {
        List<TicketTopic> ticketTopics = ticketTopicService.getAllTopicWithoutPagination();
        List<TicketCategory> ticketCategories = ticketCategoryService.getAllCategoryWithOutPagination();
        UserInfo userInfo = userInfoService.getUserInfo(principal.getName());
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("ticketRequestPojo", new TicketRequest());
        model.addAttribute("ticketTopics", ticketTopics);
        model.addAttribute("ticketCategories", ticketCategories);
        return "ticket/ticketing";
    }



    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @PostMapping("/saveTicketing")
    public String saveTicket(@ModelAttribute TicketRequest ticketRequestPojo, Model model, HttpSession session) throws Exception {
        try {
            ticketService.saveTicket(ticketRequestPojo);
            session.setAttribute("sessionMessage", "Ticket saved successfully.");
        } catch (Exception e) {
            session.setAttribute("sessionMessage", "Cannot save ticket.");
        }
        model.addAttribute("ticketRequestPojo", ticketRequestPojo);
        return "ticket/ticketing-list";
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @GetMapping("/updateTicketById/{id}")
    public String updateTicketById(@PathVariable Long id, Model model) throws MalformedURLException {
        TicketResponse ticketResponse = ticketService.getTicketById(id);
        TicketTopic ticketTopic = ticketTopicService.getTopicById(ticketResponse.getTicketCategory().getTicketTopic()
                .getFirst().getId());
        TicketCategory ticketCategory =  ticketCategoryService.getCategoryById(ticketResponse.getTicketCategory().getId()).getFirst();
        model.addAttribute("ticketCategory", ticketCategory);
        model.addAttribute("ticketTopic", ticketTopic);
        model.addAttribute("ticketResponse", ticketResponse);
        return "ticket/ticket-edit";
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @PostMapping("/updateTicketById")
    public String updateTicket(@RequestParam Long id, @ModelAttribute TicketRequest ticketRequest,
                                    Model model) throws Exception {
        ticketService.updateTicket(ticketRequest, id);
        model.addAttribute("message", "Ticket Updated Successfully.");
        return "redirect:/web/getAllTicketing";
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @GetMapping("/getAllTickets")
    public String getAllTickets(@RequestParam(defaultValue = "1") int pageNumber,
                              @RequestParam(defaultValue = "10") int pageSize, Model model) {
        Page<TicketResponse> tickets = ticketService.getAllTicketsWithPagination(pageNumber, pageSize);
        pageNumber = tickets.getNumber();
        pageSize = tickets.getSize();
        int totalPages = tickets.getTotalPages();
        model.addAttribute("tickets", tickets);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", totalPages);
        return "ticket/ticketing-list";
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @GetMapping("/getTicketById/{id}")
    public String getTicketById(@PathVariable Long id, Model model) throws MalformedURLException {
        TicketResponse ticketResponse  =  ticketService.getTicketById(id);
        model.addAttribute("ticketResponse", ticketResponse);
        return "ticket/ticket-details";
    }

//    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
//    @GetMapping("/getAllTickets")
//    public String getTickets(Model model) {
//        List<TicketResponse> tickets = ticketService.getAllTickets();
//        model.addAttribute("tickets", tickets);
//        return "ticket/ticketing-list";
//    }


}
