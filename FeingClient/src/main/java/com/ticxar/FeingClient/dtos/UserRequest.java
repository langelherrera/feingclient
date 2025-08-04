package com.ticxar.FeingClient.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequest {
    private String username;
    private String password;
}
