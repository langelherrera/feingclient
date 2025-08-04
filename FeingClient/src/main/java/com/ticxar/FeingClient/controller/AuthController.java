package com.ticxar.FeingClient.controller;

import com.ticxar.FeingClient.dtos.UserRequest;
import com.ticxar.FeingClient.dtos.UserResponse;
import com.ticxar.FeingClient.services.DummyJsonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private DummyJsonServices dummyJsonServices;

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody UserRequest request){
        UserResponse reponse = dummyJsonServices.login(request);
        return new ResponseEntity<>(reponse, HttpStatus.CREATED);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getLogin(){
        UserResponse reponse = dummyJsonServices.getLogin();
        return new ResponseEntity<>(reponse, HttpStatus.CREATED);
    }
}
