package com.cellpay.ticketingSystem.controller.web;

import com.cellpay.ticketingSystem.common.annotations.CustomWebController;
import com.cellpay.ticketingSystem.common.pojo.request.TicketCategoryRequest;
import com.cellpay.ticketingSystem.entity.TicketCategory;
import com.cellpay.ticketingSystem.entity.TicketTopic;
import com.cellpay.ticketingSystem.service.TicketCategoryService;
import com.cellpay.ticketingSystem.service.TicketTopicService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CustomWebController
@RequiredArgsConstructor
public class TicketCategoryWebController {
    private final TicketCategoryService ticketCategoryService;
    private final TicketTopicService ticketTopicService;

    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @GetMapping("/saveTicketCategory")
    public String showTicketCategoryForm(Model model) {
        List<TicketTopic> ticketTopics = ticketTopicService.getAllTopicWithoutPagination();
        model.addAttribute("ticketTopics", ticketTopics);
        model.addAttribute("ticketCategoryRequest", new TicketCategoryRequest());
        return "/ticket-category/ticket-category";
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @PostMapping("/saveTicketCategory")
    public String saveTicketCategory(@ModelAttribute TicketCategoryRequest ticketCategoryRequest, Model model, HttpSession session) {
        if (ticketCategoryService.saveTicketCategory(ticketCategoryRequest)){
            session.setAttribute("Message","Successfuly saved Category");
        }
        else {
            session.setAttribute("Message", "Something went Wrong");
        }
        return "/ticket-category/ticket-category";
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @GetMapping("/getAllTicketCategory")
    public String listCategories(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<TicketCategory> categories = ticketCategoryService.getAllCategoryWithPagination(page, 10);
        model.addAttribute("categories", categories);
        return "/ticket-category/ticket-category-list";
    }
}
