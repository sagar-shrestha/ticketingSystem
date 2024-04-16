package com.cellpay.ticketingSystem.service;

import com.cellpay.ticketingSystem.common.pojo.request.TicketCategoryRequest;
import com.cellpay.ticketingSystem.entity.TicketCategory;
import org.springframework.data.domain.Page;

public interface TicketCategoryService {
    void saveTicketCategory(TicketCategoryRequest ticketCategoryRequest);

    TicketCategory updateTicketCategory(TicketCategoryRequest ticketCategoryRequest, int id);

    TicketCategory getCategoryById(int id);

    Page<TicketCategory> getAllCategory(int pageNo, int pageSize);
}
