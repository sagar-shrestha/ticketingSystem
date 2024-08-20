package com.cellpay.ticketingSystem.common.pojo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketCategoryRequest {

    private String category;
    private List<Integer> ticketTopic;

}
