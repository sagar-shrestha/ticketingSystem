package com.cellpay.ticketingSystem.service;

import com.cellpay.ticketingSystem.Exception.DataNotFoundException;
import com.cellpay.ticketingSystem.common.pojo.request.TicketTopicRequest;
import com.cellpay.ticketingSystem.entity.TicketCategory;
import com.cellpay.ticketingSystem.entity.TicketTopic;
import com.cellpay.ticketingSystem.repository.TicketTopicRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketTopicServiceImpl implements TicketTopicService {

    private final TicketTopicRepository ticketTopicRepository;
    private final ObjectMapper objectMapper;
    private final TicketCategoryService ticketCategoryService;

    @Override
    public boolean saveTicketTopic(TicketTopicRequest ticketTopicRequest) {
        try {
            TicketTopic ticketTopic = TicketTopic.builder().topic(ticketTopicRequest.getTopic())
                    .ticketCategory(ticketCategoryService.getCategoryById(ticketTopicRequest
                            .getTicketCategories().getFirst().getId()).getFirst())
                    .build();
            ticketTopicRepository.save(ticketTopic);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public TicketTopic updateTicketTopic(TicketTopicRequest ticketTopicRequest, int id) {
        try {
            TicketTopic existingTicketTopic = getTopicById(id);
            TicketTopic updatedTicketTopic = TicketTopic.builder()
                    .id(existingTicketTopic.getId())
                    .topic(ticketTopicRequest.getTopic())
                    .ticketCategory(ticketTopicRequest.getTicketCategories().getFirst())
                    .build();
            return ticketTopicRepository.save(updatedTicketTopic);
        } catch (RuntimeException e) {
            throw new DataNotFoundException("Something went Wrong.");
        }
    }

    @Override
    public TicketTopic getTopicById(int id) {
        return ticketTopicRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Ticket Topic not found"));
    }


    @Override
    public List<TicketTopic> getAllTopicWithoutPagination() {
        try {
            return ticketTopicRepository.findAll();
        } catch (RuntimeException e) {

            throw new DataNotFoundException("Something went Wrong.");
        }
    }

    @Override
    public List<TicketTopic> getAllTopicWithCategories() {
        return ticketTopicRepository.getAllTopicWithCategories();
    }


    @Override
    public Page<TicketTopic> getAllTopicWithPagination(int pageNumber, int pageSize) {
        try {

            Pageable pageable = PageRequest.of((pageNumber - 1), pageSize, Sort.by("topic"));
            return ticketTopicRepository.findAll(pageable);
        } catch (RuntimeException e) {
            throw new DataNotFoundException("Something went Wrong.");
        }
    }


    @Override
    public void removeSessionMessage() {
        HttpSession httpSession = (HttpSession) ((Objects.requireNonNull(RequestContextHolder.getRequestAttributes())))
                .getSessionMutex();
        httpSession.removeAttribute("sessionMessage");
    }
}
