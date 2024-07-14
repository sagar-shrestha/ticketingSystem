//package com.cellpay.ticketingSystem.controller.rest;
//
//import com.cellpay.ticketingSystem.common.pojo.response.GlobalApiResponse;
//import com.cellpay.ticketingSystem.security.entity.AuthRequest;
//import com.cellpay.ticketingSystem.security.service.JwtService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.security.Principal;
//
//@RestController
//@RequestMapping("/rest")
//@RequiredArgsConstructor
//public class LoginRestController {
//    private final JwtService jwtService;
//    private final AuthenticationManager authenticationManager;
//
//    @PostMapping("/authenticate")
//    public ResponseEntity<GlobalApiResponse> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
//        try {
//            Authentication authentication = authenticationManager
//                    .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
//                            authRequest.getPassword()));
//
//            if (authentication.isAuthenticated()) {
//                String token = jwtService.generateToken(authRequest.getUsername());
//                return ResponseEntity.ok(GlobalApiResponse
//                        .builder()
//                        .code(HttpStatus.OK.value())
//                        .Token(token)
//                        .message("Authentication successful")
//                        .status(true)
//                        .build());
//            } else {
//                throw new BadCredentialsException("Bad credentials");
//            }
//        } catch (BadCredentialsException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(GlobalApiResponse
//                    .builder()
//                    .code(HttpStatus.UNAUTHORIZED.value())
//                    .Token(null)
//                    .message("Bad credentials: " + e.getMessage())
//                    .status(false)
//                    .build());
//        }
//    }
//
//}
