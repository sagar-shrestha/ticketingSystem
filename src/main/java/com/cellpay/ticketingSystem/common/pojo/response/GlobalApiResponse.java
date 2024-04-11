package com.cellpay.ticketingSystem.common.pojo.response;

import lombok.Builder;

import java.util.List;

@Builder
public class GlobalApiResponse {

    private int code;
    private String message;
    private List<Object> data;
    private boolean status;
}
