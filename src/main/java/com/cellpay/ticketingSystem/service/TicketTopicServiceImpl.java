package com.cellpay.ticketingSystem.service;

import com.cellpay.ticketingSystem.common.pojo.request.TicketTopicRequest;
import com.cellpay.ticketingSystem.entity.TicketTopic;
import com.cellpay.ticketingSystem.repository.TicketTopicRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketTopicServiceImpl implements TicketTopicService {

    private final TicketTopicRepository ticketTopicRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void saveTicketTopic(TicketTopicRequest ticketTopicRequest) {
        TicketTopic ticketTopic = objectMapper.convertValue(ticketTopicRequest, TicketTopic.class);
//        TicketTopic ticketTopic = TicketTopic
//                .builder()
//                .topic(ticketTopicRequest.getTopic())
//                .build();
        ticketTopicRepository.save(ticketTopic);
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
    public List<TicketTopic> getAllTopic() {
        return ticketTopicRepository.findAll();
    }
}
