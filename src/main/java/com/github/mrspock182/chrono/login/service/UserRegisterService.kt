package com.github.mrspock182.chrono.login.service

import com.github.mrspock182.chrono.login.domain.dto.AuthenticationResponse
import com.github.mrspock182.chrono.login.domain.dto.UserRequest
import reactor.core.publisher.Mono

interface UserRegisterService {
   fun register(user: UserRequest) : Mono<AuthenticationResponse>
}