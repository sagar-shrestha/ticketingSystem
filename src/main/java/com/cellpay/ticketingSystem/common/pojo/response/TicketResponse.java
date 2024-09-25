package com.cellpay.ticketingSystem.common.pojo.response;

import com.cellpay.ticketingSystem.common.constant.StatusType;
import com.cellpay.ticketingSystem.entity.PaynetUserDetails;
import com.cellpay.ticketingSystem.entity.TicketCategory;
import com.cellpay.ticketingSystem.entity.TicketTopic;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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
    private List<String> images;
    private TicketTopic ticketTopic;
    private String status;
    private PaynetUserDetails paynetUserDetails;
}
