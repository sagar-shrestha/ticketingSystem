package com.cellpay.ticketingSystem.service;

import com.cellpay.ticketingSystem.common.pojo.request.TicketTopicRequest;
import com.cellpay.ticketingSystem.common.pojo.response.TicketTopicResponse;

public interface TicketTopicService {

void saveTicketTopic(TicketTopicRequest ticketTopicRequest);

TicketTopicResponse getTopicById(int id);


}
