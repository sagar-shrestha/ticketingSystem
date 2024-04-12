package com.cellpay.ticketingSystem.service;

import com.cellpay.ticketingSystem.common.pojo.request.TicketTopicRequest;
import com.cellpay.ticketingSystem.entity.TicketTopic;
import com.cellpay.ticketingSystem.repository.TicketTopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketTopicServiceImpl implements TicketTopicService {

    private final TicketTopicRepository ticketTopicRepository;

    @Override
    public void saveTicketTopic(TicketTopicRequest ticketTopicRequest) {
        TicketTopic ticketTopic = TicketTopic
                .builder()
                .topic(ticketTopicRequest.getTopic())
                .build();
        ticketTopicRepository.save(ticketTopic);
    }
}
