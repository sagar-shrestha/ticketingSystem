package com.cellpay.ticketingSystem.helper;

import com.cellpay.ticketingSystem.common.pojo.response.TicketTopicResponse;
import com.cellpay.ticketingSystem.entity.TicketTopic;
import com.cellpay.ticketingSystem.repository.TicketTopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketTopicHelper {

    private final TicketTopicRepository ticketTopicRepository;

    public TicketTopicResponse getTopicById(int id) {
        TicketTopic ticketTopic = ticketTopicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topic id not found"));
        return TicketTopicResponse.builder()
                .id(ticketTopic.getId())
                .topic(ticketTopic.getTopic())
                .build();
    }
}
