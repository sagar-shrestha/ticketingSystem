package com.cellpay.ticketingSystem.service;

import com.cellpay.ticketingSystem.common.pojo.request.TicketTopicRequest;
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
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TicketTopicServiceImpl implements TicketTopicService {

    private final TicketTopicRepository ticketTopicRepository;
    private final ObjectMapper objectMapper;

    @Override
    public boolean saveTicketTopic(TicketTopicRequest ticketTopicRequest) {
        TicketTopic ticketTopic = objectMapper.convertValue(ticketTopicRequest, TicketTopic.class);
        ticketTopicRepository.save(ticketTopic);
        return true;
    }

    @Override
    public TicketTopic updateTicketTopic(TicketTopicRequest ticketTopicRequest, int id) {
        TicketTopic existingTicketTopic = getTopicById(id);
        TicketTopic updatedTicketTopic = objectMapper.convertValue(ticketTopicRequest, TicketTopic.class);
        updatedTicketTopic.setId(existingTicketTopic.getId());
        return ticketTopicRepository.save(updatedTicketTopic);
    }

    @Override
    public TicketTopic getTopicById(int id) {
        return ticketTopicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket Topic not found"));
    }

    @Override
    public Page<TicketTopic> getAllTopic(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("topic"));
        return ticketTopicRepository.findAll(pageable);
    }

    @Override
    public void removeSessionMessage() {
        HttpSession httpSession = (HttpSession) ((Objects.requireNonNull(RequestContextHolder.getRequestAttributes())))
                .getSessionMutex();
        httpSession.removeAttribute("sessionMessage");
    }
}
