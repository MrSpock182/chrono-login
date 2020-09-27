package com.github.mrspock182.chrono.login.service;

import com.github.mrspock182.chrono.login.TestSetup;
import com.github.mrspock182.chrono.login.configuration.JwConfiguration;
import com.github.mrspock182.chrono.login.domain.dto.AuthenticationResponse;
import com.github.mrspock182.chrono.login.domain.dto.UserRequest;
import com.github.mrspock182.chrono.login.domain.enumerable.JwtRoles;
import com.github.mrspock182.chrono.login.exception.InternalServerService;
import com.github.mrspock182.chrono.login.exception.NotFound;
import com.github.mrspock182.chrono.login.repository.UserRepository;
import com.github.mrspock182.chrono.login.repository.orm.UserOrm;
import com.github.mrspock182.chrono.login.service.implementation.UserAuthenticationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Mono;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserAuthenticationServiceTest extends TestSetup {

    @InjectMocks
    private UserAuthenticationServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private JwConfiguration configuration;

    @Override
    public void init() {}

    public UserOrm getUser() {
        return new UserOrm("xpto", "password", true, Collections.singletonList(JwtRoles.ROLE_USER));
    }

    @Test
    public void authenticationTest() {
        when(repository.findUser(anyString(), anyString())).thenReturn(Mono.just(getUser()));
        lenient().when(configuration.generateToken(any(UserOrm.class))).thenReturn("token");
        Mono<AuthenticationResponse> auth = service.authentication(new UserRequest("xpto", "password"));
        assertEquals(auth.block().getToken(), "token");
        verify(repository).findUser(anyString(), anyString());
        verify(configuration).generateToken(any(UserOrm.class));
    }

    @Test(expected = InternalServerService.class)
    public void authenticationTestInternalServerService() {
        when(repository.findUser(anyString(), anyString())).thenThrow(new InternalServerService());
        lenient().when(configuration.generateToken(any(UserOrm.class))).thenReturn("token");
        Mono<AuthenticationResponse> auth = service.authentication(new UserRequest("xpto", "password"));
        assertEquals(auth.block().getToken(), "token");
        verify(repository).findUser(anyString(), anyString());
        verify(configuration).generateToken(any(UserOrm.class));
    }

    @Test(expected = NotFound.class)
    public void authenticationTestNotFound() {
        when(repository.findUser(anyString(), anyString())).thenThrow(new NotFound());
        lenient().when(configuration.generateToken(any(UserOrm.class))).thenReturn("token");
        Mono<AuthenticationResponse> auth = service.authentication(new UserRequest("xpto", "password"));
        assertEquals(auth.block().getToken(), "token");
        verify(repository).findUser(anyString(), anyString());
        verify(configuration).generateToken(any(UserOrm.class));
    }

}
