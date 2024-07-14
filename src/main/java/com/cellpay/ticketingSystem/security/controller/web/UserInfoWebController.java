package com.cellpay.ticketingSystem.security.controller.web;

import com.cellpay.ticketingSystem.common.pojo.response.GlobalApiResponse;
import com.cellpay.ticketingSystem.helper.Message;
import com.cellpay.ticketingSystem.security.entity.UserInfo;
import com.cellpay.ticketingSystem.security.repository.UserInfoRepository;
import com.cellpay.ticketingSystem.security.service.UserInfoService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/super")
@RequiredArgsConstructor
public class UserInfoWebController {

    private final UserInfoService userInfoService;
    private final UserInfoRepository userInfoRepository;
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

//    @GetMapping("/")
//    public String addCommonData(Model model, Principal principal) {
//        String userName = principal.getName();
//        System.out.println("Logged-in username: " + userName);
//
//        UserInfo userInfo = userInfoRepository.findByUsername(userName);
//
//        if (userInfo == null) {
//            System.out.println("No UserInfo found for username: " + userName);
//        } else {
//            System.out.println("UserInfo found: " + userInfo.getUsername() + ", " + userInfo.getEmail());
//        }
//
//        model.addAttribute("userInfo", userInfo);
//        return "/layout_fragments/base";
//    }
//
//
//
//
//
//    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
//    @GetMapping("/ChangePassword")
//    public String showChangePasswordPage(Model model, HttpSession session) {
//        // Retrieve and remove the message from the session to ensure it's displayed only once
//        Message message = (Message) session.getAttribute("message");
//        if (message != null) {
//            model.addAttribute("message", message);
//            session.removeAttribute("message");
//        }
//        return "User_details/Change_Password";
//    }
//
//
//    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
////    @PostMapping(value = "/PasswordChange")
//    @RequestMapping(value = "/PasswordChange", method = {RequestMethod.GET, RequestMethod.POST})
//    public String changePassword(String oldPassword, String newPassword, Principal principal, HttpSession session) {
//        String userName = principal.getName();
//        UserInfo user = this.userInfoRepository.findByUsername(userName);
//
//        if (oldPassword == null || oldPassword.isEmpty()) {
//            session.setAttribute("message", new Message("Old password cannot be empty!", "danger"));
//            return "User_details/Change_Password";
//        }
//
//        if (this.bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
//            user.setPassword(this.bCryptPasswordEncoder.encode(newPassword));
//            this.userInfoRepository.save(user);
//            session.setAttribute("message", new Message("Change Successful!", "success"));
//        } else {
//            session.setAttribute("message", new Message("Wrong Old Password!", "danger"));
//        }
//        return "User_details/Change_Password";
//    }
//
//    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
//    @GetMapping("/updateProfile")
//    public String showUpdateProfilePage(Model model, HttpSession session) {
//        Message message = (Message) session.getAttribute("message");
//        if (message != null) {
//            model.addAttribute("message", message);
//            session.removeAttribute("message");
//        }
//        return "User_details/Update_Profile";
//    }
//
//    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
////    @GetMapping("/updateProfile")
//    @PostMapping("/updateProfile")
//    public String updateProfilePage(Model model, Principal principal, HttpSession session) {
//        try {
//            String userName = principal.getName();
//            UserInfo userInfo = userInfoRepository.findByUsername(userName);
//
//            if (userInfo == null) {
//                throw new RuntimeException("User not found for username: " + userName);
//            }
//
//            model.addAttribute("user", userInfo); // Add user to the model
//            return "User_details/Update_Profile";
//        } catch (Exception e) {
//            session.setAttribute("message", new Message(e.getMessage(), "danger"));
//            return "User_details/Update_Profile"; // Return to the same view with error message
//        }
//    }

}
