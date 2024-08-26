package com.cellpay.ticketingSystem.service;

import com.cellpay.ticketingSystem.security.entity.UserInfo;
import com.cellpay.ticketingSystem.security.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService{


     private final UserInfoRepository userInfoRepository;

    @Override
    public void saveUserInfo(UserInfo userInfo) {

        userInfoRepository.save(userInfo);
    }

    @Override
    public UserInfo getUserInfo(String username) {
        return userInfoRepository.findByUsername(username);
    }
}
