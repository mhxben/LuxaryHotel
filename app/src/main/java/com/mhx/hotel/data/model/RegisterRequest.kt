package com.mhx.hotel.data.model

data class RegisterRequest(
    val fullName: String,
    val username: String,
    val password: String,
    val dateOfBirth: String
)
