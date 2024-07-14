package com.cellpay.ticketingSystem.common.pojo.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PasswordChangeRequest {
    private String oldPassword;
    private String newPassword;
}
