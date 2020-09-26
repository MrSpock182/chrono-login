package com.github.mrspock182.chrono.login.domain.dto

data class UserRequest(@get:JvmName("getUsername") val username: String,
                  @get:JvmName("getPassword") val password: String)