package com.github.mrspock182.chrono.login.api

import com.github.mrspock182.chrono.login.domain.dto.AuthenticationResponse
import com.github.mrspock182.chrono.login.domain.dto.UserRequest
import com.github.mrspock182.chrono.login.service.UserAuthenticationService
import com.github.mrspock182.chrono.login.service.UserRegisterService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping(value = ["/chrono/login/user"])
class UserApi(private val authService: UserAuthenticationService,
              private val registerService: UserRegisterService) {

    @PostMapping(value = ["/register"])
    fun register(@RequestBody request: UserRequest): Mono<AuthenticationResponse> {
        return registerService.register(request)
    }

    @PostMapping(value = ["/authentication"])
    fun authentication(@RequestBody request: UserRequest): Mono<AuthenticationResponse> {
        return authService.authentication(request)
    }

}