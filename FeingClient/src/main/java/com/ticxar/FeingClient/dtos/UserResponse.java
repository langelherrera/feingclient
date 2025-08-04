package com.ticxar.FeingClient.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse
{
    private String accessToken;
    private String refreshToken;
    private int id;
    private String    username;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String image;
}
