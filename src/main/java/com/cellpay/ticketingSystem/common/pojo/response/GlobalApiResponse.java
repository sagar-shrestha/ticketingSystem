package com.cellpay.ticketingSystem.common.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GlobalApiResponse {

    private int code;
    private String message;
    private List<Object> data;
    private boolean status;
}
