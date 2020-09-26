package com.github.mrspock182.chrono.login.service

import com.github.mrspock182.chrono.login.domain.dto.AuthenticationResponse
import com.github.mrspock182.chrono.login.domain.dto.UserRequest
import reactor.core.publisher.Mono

interface UserAuthenticationService {
    fun authentication(user: UserRequest) : Mono<AuthenticationResponse>
}