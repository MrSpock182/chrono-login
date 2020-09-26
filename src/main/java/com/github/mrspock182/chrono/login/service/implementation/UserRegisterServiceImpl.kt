package com.github.mrspock182.chrono.login.service.implementation

import com.github.mrspock182.chrono.login.adapter.DtoToOrmAdapter
import com.github.mrspock182.chrono.login.configuration.JwConfiguration
import com.github.mrspock182.chrono.login.domain.dto.AuthenticationResponse
import com.github.mrspock182.chrono.login.domain.dto.UserRequest
import com.github.mrspock182.chrono.login.repository.UserRepository
import com.github.mrspock182.chrono.login.repository.orm.UserOrm
import com.github.mrspock182.chrono.login.service.UserRegisterService
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserRegisterServiceImpl(private val jw: JwConfiguration,
                              private val repository: UserRepository,
                              private val adapter: DtoToOrmAdapter<UserRequest, UserOrm>) : UserRegisterService {

    override fun register(user: UserRequest): Mono<AuthenticationResponse> {
        return repository.save(adapter.cast(user))
                .flatMap{userOrm -> Mono.just(AuthenticationResponse(jw.generateToken(userOrm)))}
    }

}