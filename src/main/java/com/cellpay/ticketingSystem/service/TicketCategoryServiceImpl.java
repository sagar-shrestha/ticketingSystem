package com.cellpay.ticketingSystem.service;

import com.cellpay.ticketingSystem.common.pojo.request.TicketCategoryRequest;
import com.cellpay.ticketingSystem.common.pojo.request.TicketRequest;
import com.cellpay.ticketingSystem.entity.TicketCategory;
import com.cellpay.ticketingSystem.repository.TicketCategoryRepository;
import com.cellpay.ticketingSystem.repository.TicketTopicRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TicketCategoryServiceImpl implements TicketCategoryService {

    private final TicketCategoryRepository ticketCategoryRepository;
    private final TicketTopicRepository ticketTopicRepository;
    private final ObjectMapper objectMapper;
    private final TicketTopicService ticketTopicService;


    @Override
    public boolean saveTicketCategory(TicketRequest ticketCategoryRequest) {
        return false;
    }

    @Override
    @Transactional
    public boolean saveTicketCategory(TicketCategoryRequest ticketCategoryRequest) {
        TicketCategory ticketCategory = TicketCategory
                .builder()
                .category(ticketCategoryRequest.getCategory())
                .ticketTopic(ticketTopicService.getTopicById(ticketCategoryRequest.getTicketTopic()))
                .build();
        ticketCategoryRepository.save(ticketCategory);
        return false;
    }

    @Override
    @Transactional
    public TicketCategory updateTicketCategory(TicketCategoryRequest ticketCategoryRequest, int id) {
        TicketCategory existingTicketCategory = getCategoryById(id);
        TicketCategory updatedTicketCategory = TicketCategory
                .builder()
                .id(existingTicketCategory.getId())
                .category(ticketCategoryRequest.getCategory())
                .ticketTopic(ticketTopicService.getTopicById(ticketCategoryRequest.getTicketTopic()))
                .build();
        return ticketCategoryRepository.save(updatedTicketCategory);
    }

    @Override
    public TicketCategory getCategoryById(int id) {
        return ticketCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket Category not found."));
    }

    @Override
    public Page<TicketCategory> getAllCategory(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("category").ascending());
        return ticketCategoryRepository.findAll(pageable);
    }





    public void removeSessionMessage() {
        HttpSession httpSession = (HttpSession) ((Objects.requireNonNull(RequestContextHolder.getRequestAttributes())))
                .getSessionMutex();
        httpSession.removeAttribute("sessionMessage");
    }

}
