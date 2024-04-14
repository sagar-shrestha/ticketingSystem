package com.cellpay.ticketingSystem.service;

import com.cellpay.ticketingSystem.common.pojo.request.TicketCategoryRequest;
import com.cellpay.ticketingSystem.common.pojo.response.TicketTopicResponse;
import com.cellpay.ticketingSystem.entity.TicketCategory;
import com.cellpay.ticketingSystem.entity.TicketTopic;
import com.cellpay.ticketingSystem.repository.TicketCategoryRepository;
import com.cellpay.ticketingSystem.repository.TicketTopicRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TicketCategoryServiceImpl implements TicketCategoryService {

    private final TicketCategoryRepository ticketCategoryRepository;
    private final TicketTopicRepository ticketTopicRepository;
    private final ObjectMapper objectMapper;
    private final TicketTopicService ticketTopicService;

    @Override
    @Transactional
    public void saveTicketCategory(TicketCategoryRequest ticketCategoryRequest) {
        TicketTopic ticketTopic = ticketTopicRepository.findById(ticketCategoryRequest.getTicketTopic())
                .orElseThrow(() -> new RuntimeException("Topic not found."));
//        TicketTopicResponse ticketTopicResponse = ticketTopicService
//                .getTopicById(ticketCategoryRequest.getTicketTopic());
//        TicketTopic ticketTopic = TicketTopic.builder()
//                .id(ticketTopicResponse.getId())
//                .topic(ticketTopicResponse.getTopic())
//                .build();
        TicketCategory ticketCategory = TicketCategory
                .builder()
                .category(ticketCategoryRequest.getCategory())
                .ticketTopic(ticketTopic)
                .build();
        ticketCategoryRepository.save(ticketCategory);
    }
}
