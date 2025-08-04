package com.ticxar.FeingClient.controller;

import com.ticxar.FeingClient.exeptions.LoginException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class GlobalExeptionHandler {

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<?> handlePrestamoException(LoginException ex){
        return  ResponseEntity.badRequest().body(Collections.singletonMap("msg",ex.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return  ResponseEntity.badRequest().body(Collections.singletonMap("msg",ex.getMessage()));
    }


}
