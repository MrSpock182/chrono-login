package com.github.mrspock182.chrono.login.adapter.implementation

import com.github.mrspock182.chrono.login.adapter.DtoToOrmAdapter
import com.github.mrspock182.chrono.login.domain.dto.UserRequest
import com.github.mrspock182.chrono.login.domain.enumerable.JwtRoles
import com.github.mrspock182.chrono.login.repository.orm.UserOrm
import org.springframework.stereotype.Component

@Component
class DtoToOrmUserAdapter : DtoToOrmAdapter<UserRequest, UserOrm> {

    override fun cast(t1: UserRequest): UserOrm {
        return UserOrm(t1.username, t1.password, true, listOf(JwtRoles.ROLE_USER))
    }
}