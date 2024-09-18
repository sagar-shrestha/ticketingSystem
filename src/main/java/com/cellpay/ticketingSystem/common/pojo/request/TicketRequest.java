package com.cellpay.ticketingSystem.common.pojo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;



@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequest {

    private int ticketCategory;
    private int ticketTopic;
    private String description;
    private List<MultipartFile> images;
//    private TicketTopicRequest ticketTopicRequest;
//    private PaynetUserDetailsRequest paynetUserDetailsRequest;
    private Long id;
    private String memberId;
    private String memberType;
    private String memberName;
    private String username;
}
