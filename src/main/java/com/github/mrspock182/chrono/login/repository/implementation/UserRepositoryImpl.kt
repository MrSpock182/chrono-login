package com.github.mrspock182.chrono.login.repository.implementation

import com.github.mrspock182.chrono.login.exception.InternalServerService
import com.github.mrspock182.chrono.login.exception.NotFound
import com.github.mrspock182.chrono.login.repository.UserRepository
import com.github.mrspock182.chrono.login.repository.orm.UserOrm
import org.springframework.stereotype.Component
import reactor.core.Exceptions
import reactor.core.publisher.Mono

@Component
class UserRepositoryImpl(private val repository: UserRepositoryWithElasticSearch) : UserRepository {

    override fun save(user: UserOrm): Mono<UserOrm> {
        return repository.save(user)
                .doOnError{InternalServerService()}
    }

    override fun findUser(username: String, password: String): Mono<UserOrm> {
        return repository.findByIdAndPassword(username, password)
                .switchIfEmpty(Mono.error(Exceptions.propagate(NotFound("User don't exists"))))
                .doOnError{InternalServerService()}
    }
}