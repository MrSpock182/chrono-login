package com.github.mrspock182.chrono.login.service.implementation

import com.github.mrspock182.chrono.login.configuration.JwConfiguration
import com.github.mrspock182.chrono.login.domain.dto.AuthenticationResponse
import com.github.mrspock182.chrono.login.domain.dto.UserRequest
import com.github.mrspock182.chrono.login.repository.UserRepository
import com.github.mrspock182.chrono.login.service.UserAuthenticationService
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserAuthenticationServiceImpl(private val jw: JwConfiguration,
                                    private val repository: UserRepository) : UserAuthenticationService {

    override fun authentication(user: UserRequest): Mono<AuthenticationResponse> {
        return repository.findUser(user.username, user.password)
                .flatMap{ userOrm -> Mono.just(AuthenticationResponse(jw.generateToken(userOrm)))}
    }
}