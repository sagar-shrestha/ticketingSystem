//package com.cellpay.ticketingSystem.controller.web;
//
//import com.cellpay.ticketingSystem.common.util.ApplicationHelper;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//@Slf4j
//@RequiredArgsConstructor
//public class LoginWebController {
//
//    @GetMapping({"", "/", "/login"})
//    public String index() {
//        return "redirect:/ticket/login";
//    }
//
//    @GetMapping("/ticket/login")
//    public String login() {
//        if (ApplicationHelper.isAuthenticated()) {
//            return "redirect:/web/home";
//        }
//        return "login";
//    }
//
//
//}
