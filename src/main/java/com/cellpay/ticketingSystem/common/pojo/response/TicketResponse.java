package com.cellpay.ticketingSystem.common.pojo.response;

import com.cellpay.ticketingSystem.common.pojo.request.TicketTopicRequest;
import com.cellpay.ticketingSystem.entity.PaynetUserDetails;
import com.cellpay.ticketingSystem.entity.TicketCategory;
import lombok.*;


import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketResponse {
    private Long id;
    private List<TicketCategory> ticketCategory;
    private String description;
    private List<Integer> imageId;
    private TicketTopicRequest ticketTopicRequest;
    private PaynetUserDetails paynetUserDetails;
}
