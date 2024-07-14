package com.cellpay.ticketingSystem.service;

import com.cellpay.ticketingSystem.common.pojo.request.PasswordChangeRequest;
import com.cellpay.ticketingSystem.helper.Message;

import java.security.Principal;

public interface UserServices {
    Message changePassword(PasswordChangeRequest passwordChangeRequest, Principal principal);
}
