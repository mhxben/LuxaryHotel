package com.mhx.hotel.data.model

data class RegisterResponse(
    val email: String,
    val first_name: String,
    val last_name: String,
    val role: String,
    val username: String
)