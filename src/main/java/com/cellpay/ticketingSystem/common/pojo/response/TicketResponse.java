package com.cellpay.ticketingSystem.common.pojo.response;

import com.cellpay.ticketingSystem.entity.TicketCategory;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketResponse {
    private Long id;
    private TicketCategory ticketCategory;
    private String description;
}
