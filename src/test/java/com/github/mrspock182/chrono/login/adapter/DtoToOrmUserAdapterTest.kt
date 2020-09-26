package com.github.mrspock182.chrono.login.adapter

import com.github.mrspock182.chrono.login.TestSetup
import com.github.mrspock182.chrono.login.adapter.implementation.DtoToOrmUserAdapter
import com.github.mrspock182.chrono.login.domain.dto.UserRequest
import com.github.mrspock182.chrono.login.domain.enumerable.JwtRoles
import com.github.mrspock182.chrono.login.repository.orm.UserOrm
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class DtoToOrmUserAdapterTest : TestSetup() {

    @InjectMocks
    private val adapter: DtoToOrmUserAdapter?= null

    override fun init() {}

    @Test
    fun castTest() {
       val userOrm: UserOrm = adapter!!.cast(UserRequest("XPTO", "Senha@111"))
        assertEquals(userOrm.username, "XPTO")
        assertEquals(userOrm.password, "Senha@111")
        assertEquals(userOrm.isEnabled, true)
        assertEquals(userOrm.getRoles(), listOf(JwtRoles.ROLE_USER))
    }

}