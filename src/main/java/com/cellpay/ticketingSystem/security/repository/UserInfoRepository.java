package com.cellpay.ticketingSystem.security.repository;

import com.cellpay.ticketingSystem.security.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    @Query("SELECT u FROM UserInfo u WHERE u.username = :username")
    UserInfo findByUsername(@Param("username") String username);
}
