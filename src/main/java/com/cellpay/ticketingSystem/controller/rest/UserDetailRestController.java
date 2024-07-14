package com.cellpay.ticketingSystem.controller.rest;

import com.cellpay.ticketingSystem.common.annotations.CustomWebController;
import com.cellpay.ticketingSystem.common.pojo.request.PasswordChangeRequest;
import com.cellpay.ticketingSystem.helper.Message;
import com.cellpay.ticketingSystem.security.entity.UserInfo;
import com.cellpay.ticketingSystem.security.repository.UserInfoRepository;
import com.cellpay.ticketingSystem.service.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@CustomWebController
@RequiredArgsConstructor
@RequestMapping("rest")
public class UserDetailRestController {

    private final UserServices userServices;


    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChangeRequest passwordChangeRequest, Principal principal) {
        Message message =  userServices.changePassword(passwordChangeRequest,principal);

        if(message.getType().equals("success")){
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } else if (message.getType().equals("danger")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        }

    }
