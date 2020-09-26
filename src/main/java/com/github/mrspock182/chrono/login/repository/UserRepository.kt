package com.github.mrspock182.chrono.login.repository

import com.github.mrspock182.chrono.login.repository.orm.UserOrm
import reactor.core.publisher.Mono

interface UserRepository {
    fun save(user: UserOrm) : Mono<UserOrm>

    fun findUser(username: String, password: String) : Mono<UserOrm>
}