package com.cellpay.ticketingSystem.security.controller.rest;

import com.cellpay.ticketingSystem.common.annotations.CustomRestController;
import com.cellpay.ticketingSystem.common.pojo.response.GlobalApiResponse;
import com.cellpay.ticketingSystem.security.entity.UserInfo;
import com.cellpay.ticketingSystem.security.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CustomRestController
@RequestMapping("/super")
@RequiredArgsConstructor
public class UserInfoRestController {

    private final UserInfoService userInfoService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

  //  @PreAuthorize("hasAuthority('SUPER_SUPER')")
    @PostMapping
    public ResponseEntity<GlobalApiResponse> saveRestUserInfo(@RequestBody UserInfo userInfo) {
        String password = userInfo.getPassword();
        userInfo.setPassword(bCryptPasswordEncoder.encode(password));
        userInfoService.saveUserInfo(userInfo);
        return ResponseEntity.ok(GlobalApiResponse.builder()
                .code(HttpStatus.CREATED.value())
                .data(null)
                .message("User Created Successfully")
                .status(true)
                .build());
    }
}
