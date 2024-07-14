package com.cellpay.ticketingSystem.service;

import com.cellpay.ticketingSystem.common.pojo.request.PasswordChangeRequest;
import com.cellpay.ticketingSystem.helper.Message;
import com.cellpay.ticketingSystem.security.entity.UserInfo;
import com.cellpay.ticketingSystem.security.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserServicesImpl implements UserServices {

    private final UserInfoRepository userInfoRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public Message changePassword(PasswordChangeRequest passwordChangeRequest, Principal principal) {
        String userName=principal.getName();
        UserInfo userInfo=userInfoRepository.findByUsername(userName);

        if (passwordChangeRequest.getOldPassword()==null||passwordChangeRequest.getOldPassword().isEmpty()) {
            return  new Message("Old password cannnot be empty!","danger");
        }
        if (this.bCryptPasswordEncoder.matches(passwordChangeRequest.getOldPassword(),userInfo.getPassword())) {
            userInfo.setPassword(bCryptPasswordEncoder.encode(passwordChangeRequest.getNewPassword()));
            this.userInfoRepository.save(userInfo);
            return new Message("Password changed successfully!","success");
        }
        else{
            return new Message("Old password does not match!","danger");
        }
    }
}
