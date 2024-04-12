package com.cellpay.ticketingSystem.common.pojo.request;

import com.cellpay.ticketingSystem.entity.TicketTopic;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@Builder
public class TicketRequest {

    private TicketTopic ticketTopic;
    private List<MultipartFile> photos;
}
