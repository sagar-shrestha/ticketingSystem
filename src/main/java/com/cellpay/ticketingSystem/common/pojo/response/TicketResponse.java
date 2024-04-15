package com.cellpay.ticketingSystem.common.pojo.response;

import com.cellpay.ticketingSystem.entity.TicketCategory;
import lombok.*;
import org.springframework.core.io.Resource;

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
    private List<Integer> photoId;
    private List<Resource> photo;

}
