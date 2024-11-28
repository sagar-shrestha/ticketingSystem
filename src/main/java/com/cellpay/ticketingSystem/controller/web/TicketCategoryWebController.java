package com.cellpay.ticketingSystem.controller.web;

import com.cellpay.ticketingSystem.common.annotations.CustomWebController;
import com.cellpay.ticketingSystem.common.pojo.request.TicketCategoryRequest;
import com.cellpay.ticketingSystem.common.pojo.request.TicketTopicRequest;
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

    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @GetMapping("/saveTicketCategory")
    public String showTicketCategoryForm(Model model) {
        //  List<TicketTopic> ticketTopics = ticketTopicService.getAllTopicWithoutPagination();
        //model.addAttribute("ticketTopics", ticketTopics);
        model.addAttribute("ticketCategoryRequest", new TicketCategoryRequest());
        return "ticket-category/ticket-category";
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
        return "redirect:getAllTicketCategory";
    }


    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @GetMapping("/updateCategoryById/{id}")
    public String updateTopicById(@PathVariable int id, Model model) {
        TicketCategory ticketCategory =  ticketCategoryService.getCategoryById(id).getFirst();
        model.addAttribute("ticketCategory", ticketCategory);
        return "ticket-category/ticket-category-edit";
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @PostMapping("/updateCategoryById")
    public String updateTicketTopic(@RequestParam int id, @ModelAttribute  TicketCategoryRequest ticketCategoryRequest,
                                    Model model) {
         TicketCategory ticketCategory = ticketCategoryService.updateTicketCategory(ticketCategoryRequest, id);
        model.addAttribute("ticketCategory", ticketCategory);
        model.addAttribute("message", "Topic Updated Successfully.");
        return "redirect:/web/getAllTicketCategory";
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @GetMapping("/getCategoryById/{id}")
    public String getCategoryById(@PathVariable int id, Model model) {
        TicketCategory ticketCategory  =  ticketCategoryService.getCategoryById(id).getFirst();
        model.addAttribute("ticketCategory", ticketCategory);
        return "ticket-category/ticket-category-details";
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @GetMapping("/getAllTicketCategory")
    public String listCategories(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<TicketCategory> categories = ticketCategoryService.getAllCategoryWithPagination(page, 10);
        model.addAttribute("categories", categories);
        return "ticket-category/ticket-category-list";
    }
}
