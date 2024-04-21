package com.cellpay.ticketingSystem.security.service;

import com.cellpay.ticketingSystem.security.entity.UserInfo;
import com.cellpay.ticketingSystem.security.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService{


    private final UserInfoRepository userInfoRepository;
   //  private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void saveUserInfo(UserInfo userInfo) {

        userInfoRepository.save(userInfo);
    }
}
