package com.cellpay.ticketingSystem.common.pojo.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaynetUserDetailsRequest {

    private Long id;
    private String memberId;
    private String memberType;
    private String memberName;
    private String username;
}
