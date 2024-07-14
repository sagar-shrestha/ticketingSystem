package com.cellpay.ticketingSystem.security.controller.rest;

import com.cellpay.ticketingSystem.common.pojo.response.GlobalApiResponse;
import com.cellpay.ticketingSystem.security.Exception.AuthenticationException;
import com.cellpay.ticketingSystem.security.entity.AuthRequest;
import com.cellpay.ticketingSystem.security.service.JwtService;
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
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public ResponseEntity<GlobalApiResponse> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
                            authRequest.getPassword()));

            if (authentication.isAuthenticated()) {
                String token = jwtService.generateToken(authRequest.getUsername());
                return ResponseEntity.ok(GlobalApiResponse
                        .builder()
                        .code(HttpStatus.OK.value())
                        .aceessToken(token)
                        .message("Authentication successful")
                        .status(true)
                        .build());
            } else {
                throw new AuthenticationException("Bad credentials");
            }
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("Username or Password Invallid " + e.getMessage());
        }
    }
}