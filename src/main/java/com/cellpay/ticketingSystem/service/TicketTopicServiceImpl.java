package com.cellpay.ticketingSystem.service;

import com.cellpay.ticketingSystem.common.pojo.request.TicketTopicRequest;
import com.cellpay.ticketingSystem.common.pojo.response.TicketTopicResponse;
import com.cellpay.ticketingSystem.entity.TicketTopic;
import com.cellpay.ticketingSystem.helper.TicketTopicHelper;
import com.cellpay.ticketingSystem.repository.TicketTopicRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketTopicServiceImpl implements TicketTopicService {

    private final TicketTopicRepository ticketTopicRepository;
    private final TicketTopicHelper ticketTopicHelper;
    private final ObjectMapper objectMapper;

    @Override
    public void saveTicketTopic(TicketTopicRequest ticketTopicRequest) {
        TicketTopic ticketTopic =    objectMapper.convertValue(ticketTopicRequest, TicketTopic.class);
//        TicketTopic ticketTopic = TicketTopic
//                .builder()
//                .topic(ticketTopicRequest.getTopic())
//                .build();
        ticketTopicRepository.save(ticketTopic);
    }

    @Override
    public TicketTopicResponse getTopicById(int id) {
        return ticketTopicHelper.getTopicById(id);
    }
}
