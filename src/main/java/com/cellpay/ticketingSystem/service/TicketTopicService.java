package com.cellpay.ticketingSystem.service;

import com.cellpay.ticketingSystem.common.pojo.request.TicketTopicRequest;
import com.cellpay.ticketingSystem.entity.TicketTopic;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TicketTopicService {

boolean saveTicketTopic(TicketTopicRequest ticketTopicRequest);

TicketTopic updateTicketTopic(TicketTopicRequest ticketTopicRequest, int id);

List<TicketTopic> getTopicById(int id);

List<TicketTopic> getAllTopicWithoutPagination();

Page<TicketTopic> getAllTopicWithPagination(int pageNumber, int pageSize);

void removeSessionMessage();

}
