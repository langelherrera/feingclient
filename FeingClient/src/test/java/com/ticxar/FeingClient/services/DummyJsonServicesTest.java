package com.ticxar.FeingClient.services;

import com.ticxar.FeingClient.client.token.TokenStore;
import com.ticxar.FeingClient.dtos.UserRequest;
import com.ticxar.FeingClient.dtos.UserResponse;
import com.ticxar.FeingClient.exeptions.LoginException;
import com.ticxar.FeingClient.services.client.AuthenticationClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DummyJsonServicesTest {

    @Mock
    private AuthenticationClient authenticationClient;

    @Mock
    private LoginLogServices loginLogServices;

    @Mock
    private TokenStore tokenStore;

    @InjectMocks
    private DummyJsonServices dummyJsonServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_shouldReturnResponse_whenValidCredentials() {

        UserRequest request = new UserRequest("testuser", "testpass");
        UserResponse mockResponse = new UserResponse();
        mockResponse.setAccessToken("access123");
        mockResponse.setRefreshToken("refresh123");

        when(authenticationClient.login(request)).thenReturn(mockResponse);

        UserResponse response = dummyJsonServices.login(request);

        assertNotNull(response);
        assertEquals("access123", response.getAccessToken());
        verify(tokenStore).setAccessToken("access123");
        verify(tokenStore).setRefreshToken("access123");
        verify(loginLogServices).saveLog(mockResponse);
    }

    @Test
    void login_shouldThrowException_whenUsernameAndPasswordNull() {
        UserRequest request = new UserRequest(null, null);

        LoginException exception = assertThrows(LoginException.class, () ->
                dummyJsonServices.login(request)
        );

        assertEquals("Los parametros username y password son obligatorios", exception.getMessage());
    }

    @Test
    void login_shouldThrowException_whenOnlyPasswordNull() {
        UserRequest request = new UserRequest("user", null);

        LoginException exception = assertThrows(LoginException.class, () ->
                dummyJsonServices.login(request)
        );

        assertEquals("El password es obligatoria", exception.getMessage());
    }

    @Test
    void login_shouldThrowException_whenOnlyUsernameNull() {
        UserRequest request = new UserRequest(null, "pass");

        LoginException exception = assertThrows(LoginException.class, () ->
                dummyJsonServices.login(request)
        );

        assertEquals("El username es obligatorio", exception.getMessage());
    }

    @Test
    void login_shouldThrowGenericLoginException_whenUnexpectedError() {
        UserRequest request = new UserRequest("user", "pass");

        when(authenticationClient.login(request)).thenThrow(new RuntimeException("Some error"));

        LoginException exception = assertThrows(LoginException.class, () ->
                dummyJsonServices.login(request)
        );

        assertEquals("username o password invalido", exception.getMessage());
    }
}
