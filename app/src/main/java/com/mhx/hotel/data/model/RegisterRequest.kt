package com.mhx.hotel.data.model

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String,
    val password2: String,
    val role: String,
    val fullname: String
)