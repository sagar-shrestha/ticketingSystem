package com.cellpay.ticketingSystem.Exception;


import com.cellpay.ticketingSystem.common.pojo.response.GlobalApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler(ExceptionHandel.class)
    public ResponseEntity<GlobalApiResponse> handleException(ExceptionHandel ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                GlobalApiResponse.builder()
                        .code(HttpStatus.UNAUTHORIZED.value())
                        .message(ex.getMessage())
                        .status(false)
                        .build()
        );
    }
}