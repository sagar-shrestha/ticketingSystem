package com.cellpay.ticketingSystem.service;

import com.cellpay.ticketingSystem.common.pojo.request.TicketCategoryRequest;
import com.cellpay.ticketingSystem.entity.TicketCategory;
import com.cellpay.ticketingSystem.entity.TicketTopic;
import com.cellpay.ticketingSystem.repository.TicketCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketCategoryServiceImpl implements TicketCategoryService {

    private final TicketCategoryRepository ticketCategoryRepository;

    @Override
    public void saveTicketCategory(TicketCategoryRequest ticketCategoryRequest) {

        TicketCategory ticketCategory = TicketCategory
                .builder()
                .category(ticketCategoryRequest.getCategory())
                .ticketTopic(TicketTopic.builder().id(ticketCategoryRequest.getTicketTopic()).build())
                .build();
        ticketCategoryRepository.save(ticketCategory);
    }
}
