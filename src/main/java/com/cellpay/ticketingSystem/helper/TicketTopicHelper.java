package com.cellpay.ticketingSystem.helper;

import com.cellpay.ticketingSystem.common.pojo.response.TicketTopicResponse;
import com.cellpay.ticketingSystem.entity.TicketTopic;
import com.cellpay.ticketingSystem.repository.TicketTopicRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketTopicHelper {

    private final TicketTopicRepository ticketTopicRepository;
    private final ObjectMapper objectMapper;

    public TicketTopicResponse getTopicById(int id) {
        TicketTopic ticketTopic = ticketTopicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topic id not found"));
        return objectMapper.convertValue(ticketTopic, TicketTopicResponse.class);
//        return TicketTopicResponse.builder()
//                .id(ticketTopic.getId())
//                .topic(ticketTopic.getTopic())
//                .build();
    }
}
