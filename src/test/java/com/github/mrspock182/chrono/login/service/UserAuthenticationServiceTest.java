package com.github.mrspock182.chrono.login.service;

import com.github.mrspock182.chrono.login.TestSetup;
import com.github.mrspock182.chrono.login.configuration.JwConfiguration;
import com.github.mrspock182.chrono.login.domain.dto.UserRequest;
import com.github.mrspock182.chrono.login.domain.enumerable.JwtRoles;
import com.github.mrspock182.chrono.login.repository.UserRepository;
import com.github.mrspock182.chrono.login.repository.orm.UserOrm;
import com.github.mrspock182.chrono.login.service.implementation.UserAuthenticationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.verification.VerificationMode;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;

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
    public void init() {

    }

    public UserOrm getUser() {
        return new UserOrm("xpto", "password", true, Collections.singletonList(JwtRoles.ROLE_USER));
    }

    @Test
    public void authenticationTest() {
        when(repository.findUser(anyString(), anyString())).thenReturn(Mono.just(getUser()));
        lenient().when(configuration.generateToken(any(UserOrm.class))).thenReturn("token");
        service.authentication(new UserRequest("xpto", "password"));
        verify(repository).findUser(anyString(), anyString());
        verify(configuration).generateToken(any(UserOrm.class));
    }

}
