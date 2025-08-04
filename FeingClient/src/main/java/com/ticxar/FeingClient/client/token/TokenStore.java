package com.ticxar.FeingClient.client.token;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class TokenStore {

    private String accessToken;
    private String refreshToken;
}
