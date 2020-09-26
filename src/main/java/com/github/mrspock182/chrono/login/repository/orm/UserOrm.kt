package com.github.mrspock182.chrono.login.repository.orm

import com.github.mrspock182.chrono.login.domain.enumerable.JwtRoles
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
//import org.springframework.security.core.GrantedAuthority
//import org.springframework.security.core.authority.SimpleGrantedAuthority
//import org.springframework.security.core.userdetails.UserDetails
import java.util.stream.Collectors

@Document(indexName = "chrono", type = "user")
class UserOrm(@Id private var id: String,
              private var password: String,
              private var enabled: Boolean,
              private var roles: List<JwtRoles>) : UserDetails {

    override fun getUsername(): String? {
        return id
    }

    override fun getPassword(): String? {
        return password
    }

    override fun isEnabled(): Boolean {
        return enabled
    }

    fun getRoles(): List<JwtRoles>? {
        return roles
    }

    override fun isAccountNonExpired(): Boolean {
        return false
    }

    override fun isAccountNonLocked(): Boolean {
        return false
    }

    override fun isCredentialsNonExpired(): Boolean {
        return false
    }

    override fun getAuthorities(): Collection<GrantedAuthority?>? {
        return roles.stream().map {
            authority: JwtRoles -> SimpleGrantedAuthority(authority.name)
        }.collect(Collectors.toList())
    }
}