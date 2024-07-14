package com.cellpay.ticketingSystem.security.Exception;


import com.cellpay.ticketingSystem.common.pojo.response.GlobalApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<GlobalApiResponse> handleAuthenticationException(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                GlobalApiResponse.builder()
                        .code(HttpStatus.UNAUTHORIZED.value())
                        .aceessToken(null)
                        .message(ex.getMessage())
                        .status(false)
                        .build()
        );
    }
}