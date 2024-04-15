package com.cellpay.ticketingSystem.service;

import com.cellpay.ticketingSystem.common.pojo.request.TicketTopicRequest;
import com.cellpay.ticketingSystem.common.pojo.response.TicketTopicResponse;
import com.cellpay.ticketingSystem.entity.TicketTopic;

import java.util.List;

public interface TicketTopicService {

void saveTicketTopic(TicketTopicRequest ticketTopicRequest);

TicketTopic updateTicketTopic(TicketTopicRequest ticketTopicRequest, int id);

TicketTopic getTopicById(int id);

List<TicketTopic> getAllTopic();


}
