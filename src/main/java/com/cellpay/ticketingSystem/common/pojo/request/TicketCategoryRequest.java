package com.cellpay.ticketingSystem.common.pojo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketCategoryRequest {

    private String category;
    private int ticketTopic;

}
