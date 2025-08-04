package com.ticxar.FeingClient.client;

import com.ticxar.FeingClient.dtos.UserRequest;
import com.ticxar.FeingClient.dtos.UserResponse;
import com.ticxar.FeingClient.dtos.UsersList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "dummyClient", url="https://dummyjson.com")
public interface DummyJsonClient {

    @PostMapping(value = "/auth/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    UserResponse login(@RequestBody UserRequest request);

    @GetMapping("/auth/me")
    UserResponse me();

    @GetMapping("/users")
    UsersList getAllUsers();
}
