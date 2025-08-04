package com.ticxar.FeingClient.client.token;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenInterceptor implements RequestInterceptor {

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String accessToken= tokenStore.getAccessToken();

        if (accessToken != null && !accessToken.isEmpty()) {
            requestTemplate.header("Authorization", "Bearer " + accessToken);
        }
    }
}
