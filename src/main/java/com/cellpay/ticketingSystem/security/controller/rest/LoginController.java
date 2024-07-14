package com.cellpay.ticketingSystem.security.controller.rest;

import com.cellpay.ticketingSystem.common.pojo.response.GlobalApiResponse;
import com.cellpay.ticketingSystem.security.Exception.AuthenticationException;
import com.cellpay.ticketingSystem.security.entity.AuthRequest;
import com.cellpay.ticketingSystem.security.service.JwtService;
import com.cellpay.ticketingSystem.security.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/super")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/authenticate")
    public ResponseEntity<GlobalApiResponse> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        String token = loginService.authenticate(authRequest);
        return ResponseEntity.ok(GlobalApiResponse
                .builder()
                .code(HttpStatus.OK.value())
                .aceessToken(token)
                .message("Authentication successful")
                .status(true)
                .build());
    }
}