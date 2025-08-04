package com.ticxar.FeingClient.services;

import com.ticxar.FeingClient.dtos.UserResponse;
import com.ticxar.FeingClient.model.LoginLog;
import com.ticxar.FeingClient.repository.LoginLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginLogServices {

    @Autowired
    private LoginLogRepository loginLogRepository;
    public void saveLog(UserResponse response){
        LoginLog log = LoginLog.builder()
                .userName(response.getUsername())
                .accessToken(response.getAccessToken())
                .refreshToken(response.getRefreshToken())
                .build();

        loginLogRepository.save(log);
    }
}
