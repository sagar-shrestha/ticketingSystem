package com.cellpay.ticketingSystem.service;

import com.cellpay.ticketingSystem.common.pojo.request.TicketCategoryRequest;
import com.cellpay.ticketingSystem.common.pojo.request.TicketRequest;
import com.cellpay.ticketingSystem.entity.TicketCategory;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TicketCategoryService {
    boolean saveTicketCategory(TicketRequest ticketCategoryRequest);

    @Transactional
    boolean saveTicketCategory(TicketCategoryRequest ticketCategoryRequest);

    TicketCategory updateTicketCategory(TicketCategoryRequest ticketCategoryRequest, int id);

//    List<TicketCategory> getCategoryById(int categoryId);

    TicketCategory getCategoryById(int id);

    Page<TicketCategory> getAllCategory(int pageNo, int pageSize);
    List<TicketCategory> getAllCategory();
}
