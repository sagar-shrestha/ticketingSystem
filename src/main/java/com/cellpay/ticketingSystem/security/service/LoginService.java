package com.cellpay.ticketingSystem.security.service;

import com.cellpay.ticketingSystem.security.entity.AuthRequest;

public interface LoginService {
    public String authenticate(AuthRequest authRequest);
}
