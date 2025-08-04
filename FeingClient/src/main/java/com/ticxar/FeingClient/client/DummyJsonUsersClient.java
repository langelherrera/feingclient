package com.ticxar.FeingClient.client;

import com.ticxar.FeingClient.dtos.UsersList;
import com.ticxar.FeingClient.services.client.UsersClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DummyJsonUsersClient  implements UsersClient {

    @Autowired
    private DummyJsonClient dummyJsonClient;
    @Override
    public UsersList getAllUser() {
        return dummyJsonClient.getAllUsers();
    }
}
