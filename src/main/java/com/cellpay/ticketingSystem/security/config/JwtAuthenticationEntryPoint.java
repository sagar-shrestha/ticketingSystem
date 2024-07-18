//package com.cellpay.ticketingSystem.security.config;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//import io.jsonwebtoken.ExpiredJwtException;
//
//
//import java.io.IOException;
//
//@Component
//public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        Throwable cause = authException.getCause();
//        if (cause instanceof ExpiredJwtException) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT Token has expired");
//        } else {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
//        }
//    }
//}
