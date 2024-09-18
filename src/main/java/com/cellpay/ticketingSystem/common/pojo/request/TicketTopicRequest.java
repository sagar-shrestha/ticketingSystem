package com.cellpay.ticketingSystem.common.pojo.request;

import com.cellpay.ticketingSystem.entity.TicketCategory;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketTopicRequest{
//    private Integer id;
    private String topic;
    private List<TicketCategory> ticketCategories;
}
