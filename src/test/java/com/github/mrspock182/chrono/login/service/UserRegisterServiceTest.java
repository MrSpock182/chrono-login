package com.github.mrspock182.chrono.login.service;

import com.github.mrspock182.chrono.login.TestSetup;
import com.github.mrspock182.chrono.login.adapter.DtoToOrmAdapter;
import com.github.mrspock182.chrono.login.configuration.JwConfiguration;
import com.github.mrspock182.chrono.login.domain.dto.UserRequest;
import com.github.mrspock182.chrono.login.domain.enumerable.JwtRoles;
import com.github.mrspock182.chrono.login.repository.UserRepository;
import com.github.mrspock182.chrono.login.repository.orm.UserOrm;
import com.github.mrspock182.chrono.login.service.implementation.UserRegisterServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Mono;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserRegisterServiceTest extends TestSetup {

    @InjectMocks
    private UserRegisterServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private JwConfiguration configuration;

    @Mock
    private DtoToOrmAdapter<UserRequest, UserOrm> adapter;

    @Override
    public void init() {

    }

    public UserOrm getUser() {
        return new UserOrm("xpto", "password", true, Collections.singletonList(JwtRoles.ROLE_USER));
    }

    public UserRequest getUserRequest() {
        return new UserRequest("xpto", "password");
    }

    @Test
    public void registerTest() {
        when(adapter.cast(any(UserRequest.class))).thenReturn(getUser());
        lenient().when(configuration.generateToken(any(UserOrm.class))).thenReturn("token");
        when(repository.save(any(UserOrm.class))).thenReturn(Mono.just(getUser()));
        service.register(getUserRequest());
        verify(repository).save(any(UserOrm.class));
        verify(adapter).cast(any(UserRequest.class));
        verify(configuration, times(0)).generateToken(any(UserOrm.class));
    }

}
