package com.github.mrspock182.chrono.login.domain.dto

import java.util.*

class ErrorResponse(@get:JvmName("getId") val id: String,
                    @get:JvmName("getTimestamp") val timestamp: Date,
                    @get:JvmName("getPath") val path: String,
                    @get:JvmName("getStatus") val status: Int,
                    @get:JvmName("getError") val error: String,
                    @get:JvmName("getMessage") val message: String?)