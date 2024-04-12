package com.cellpay.ticketingSystem.service;

import com.cellpay.ticketingSystem.common.pojo.request.TicketTopicRequest;
import com.cellpay.ticketingSystem.entity.TicketTopic;

public interface TicketTopicService {

void saveTicketTopic(TicketTopicRequest ticketTopicRequest);
}
