package com.cellpay.ticketingSystem.controller.rest;

import com.cellpay.ticketingSystem.common.annotations.CustomWebController;
import com.cellpay.ticketingSystem.common.pojo.request.PasswordChangeRequest;
import com.cellpay.ticketingSystem.helper.Message;
import com.cellpay.ticketingSystem.security.entity.UserInfo;
import com.cellpay.ticketingSystem.security.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

@CustomWebController
@RequiredArgsConstructor
public class UserDetailRestController {

    private final UserInfoRepository userInfoRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @PostMapping("/PasswordChange")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChangeRequest passwordChangeRequest, Principal principal) {
        String userName = principal.getName();
        UserInfo user = this.userInfoRepository.findByUsername(userName);
        if (passwordChangeRequest.getOldPassword() == null || passwordChangeRequest.getOldPassword().isEmpty()) {
            return ResponseEntity.
                    badRequest().
                    body(new Message("Old password cannot be empty!", "danger"));
        }

        if (this.bCryptPasswordEncoder.matches(passwordChangeRequest.getOldPassword(), user.getPassword())) {
            user.setPassword(this.bCryptPasswordEncoder.encode(passwordChangeRequest.getNewPassword()));
            this.userInfoRepository.save(user);
            return
                    ResponseEntity.ok(new Message("Change Successful!", "success"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Message("Wrong Old Password!", "danger"));
        }
    }
}
