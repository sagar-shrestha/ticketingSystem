package com.cellpay.ticketingSystem.security.controller.web;

import com.cellpay.ticketingSystem.common.pojo.response.GlobalApiResponse;
import com.cellpay.ticketingSystem.security.entity.UserInfo;
import com.cellpay.ticketingSystem.security.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/super")
@RequiredArgsConstructor
public class UserInfoWebController {

    private final UserInfoService userInfoService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    //  @PreAuthorize("hasAuthority('SUPER_SUPER')")
    @PostMapping("/saveUserInfo")
    public ResponseEntity<GlobalApiResponse> saveUserInfoWeb(@RequestBody UserInfo userInfo) {
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

    @GetMapping("/user-management")
    public String userManagement() {
        return "user-management";
    }

    @GetMapping("/user-managements")
    public String userManagements(Model model) {
        String hello = "hello";
        model.addAttribute("hello", hello);
        return "user-management";
    }
}
