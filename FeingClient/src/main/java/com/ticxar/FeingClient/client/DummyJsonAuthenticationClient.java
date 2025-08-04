package com.ticxar.FeingClient.client;

import com.ticxar.FeingClient.dtos.UserRequest;
import com.ticxar.FeingClient.dtos.UserResponse;
import com.ticxar.FeingClient.dtos.UsersList;
import com.ticxar.FeingClient.services.client.AuthenticationClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DummyJsonAuthenticationClient implements AuthenticationClient {

    @Autowired
    private DummyJsonClient dummyJsonClient;

    @Override
    public UserResponse login(UserRequest request) {
        return dummyJsonClient.login(request);
    }

    @Override
    public UserResponse getLogin() {
        return dummyJsonClient.me();
    }


}
