package com.mhx.hotel.data.model

data class RegisterRequest(
    val confirm_password: String,
    val email: String,
    val first_name: String,
    val last_name: String,
    val password: String,
    val role: String,
    val username: String
)