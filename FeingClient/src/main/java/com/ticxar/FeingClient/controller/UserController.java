package com.ticxar.FeingClient.controller;

import com.ticxar.FeingClient.dtos.UserResponse;
import com.ticxar.FeingClient.dtos.UsersList;
import com.ticxar.FeingClient.services.DummyJsonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private DummyJsonServices dummyJsonServices;

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        UsersList users = dummyJsonServices.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
