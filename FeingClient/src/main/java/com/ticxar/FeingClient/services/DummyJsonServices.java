package com.ticxar.FeingClient.services;

import com.ticxar.FeingClient.client.DummyJsonUsersClient;
import com.ticxar.FeingClient.client.token.TokenStore;
import com.ticxar.FeingClient.dtos.UserRequest;
import com.ticxar.FeingClient.dtos.UserResponse;
import com.ticxar.FeingClient.dtos.UsersList;
import com.ticxar.FeingClient.exeptions.LoginException;
import com.ticxar.FeingClient.services.client.AuthenticationClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DummyJsonServices {

    @Autowired
    private AuthenticationClient authenticationClient;

    @Autowired
    private DummyJsonUsersClient usersClient;
    @Autowired
    private LoginLogServices loginLogServices;
    @Autowired
    private TokenStore tokenStore;
    public UserResponse login(UserRequest request){
        try{
                UserResponse response = authenticationClient.login(request);
                tokenStore.setAccessToken(response.getAccessToken());
                tokenStore.setRefreshToken(response.getAccessToken());
                loginLogServices.saveLog(response);
                return  response;
        }catch (Exception exception){

            if(request.getPassword() ==null && request.getUsername()==null){
                throw new LoginException("Los parametros username y password son obligatorios");
            }else {
                if(request.getPassword()==null){
                    throw new LoginException("El password es obligatoria");
                } else if (request.getUsername()==null) {
                    throw new LoginException("El username es obligatorio");
                }else {
                    throw new LoginException("username o password invalido");
                }
            }

        }

    }

    public UserResponse getLogin(){
        try{
            UserResponse response = authenticationClient.getLogin();
            return  response;
        }catch (Exception ex){
            throw new LoginException(ex.toString());
        }

    }

    public UsersList getAllUsers(){
        try {
            UsersList users= (UsersList) usersClient.getAllUser();
            return users;
        }catch (Exception ex){
            throw new LoginException(ex.toString());
        }
    }
}
