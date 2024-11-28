package com.cellpay.ticketingSystem.common.pojo.response;

import com.cellpay.ticketingSystem.entity.PaynetUserDetails;
import com.cellpay.ticketingSystem.entity.TicketCategory;
import com.cellpay.ticketingSystem.entity.TicketTopic;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.util.List;




@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketResponse {
    private Long id;
    private TicketCategory ticketCategory;
    private String description;
    private List<Integer> imageId;
    private TicketTopic ticketTopic;
    private PaynetUserDetails paynetUserDetails;
}
