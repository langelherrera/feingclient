package com.ticxar.FeingClient.services.client;

import com.ticxar.FeingClient.dtos.UserRequest;
import com.ticxar.FeingClient.dtos.UserResponse;
import com.ticxar.FeingClient.dtos.UsersList;

public interface AuthenticationClient {

    UserResponse login(UserRequest request);

    UserResponse getLogin();

}
