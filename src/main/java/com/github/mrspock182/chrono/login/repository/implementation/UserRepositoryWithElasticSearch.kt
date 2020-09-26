package com.github.mrspock182.chrono.login.repository.implementation

import com.github.mrspock182.chrono.login.repository.orm.UserOrm
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface UserRepositoryWithElasticSearch : ReactiveCrudRepository<UserOrm, String> {
    fun findByIdAndPassword(id: String, password: String) : Mono<UserOrm>
}