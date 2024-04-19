package com.cellpay.ticketingSystem.security.repository;

import com.cellpay.ticketingSystem.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {


    UserInfo findByUsername(String username);
}
