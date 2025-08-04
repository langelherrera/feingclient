package com.ticxar.FeingClient.dtos;

import lombok.Data;

import java.util.List;

@Data
public class UsersList {
    private List<UserResponse> users;
}
