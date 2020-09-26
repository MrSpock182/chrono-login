package com.github.mrspock182.chrono.login.adapter

interface DtoToOrmAdapter<T1, T2> {
    fun cast(t1: T1) : T2
}