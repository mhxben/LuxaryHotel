package com.mhx.hotel.data.model

data class LoginResponse(
    val access: String,
    val refresh: String,
    val role: String,
    val user_id: Int,
    val username: String,
    val fullname: String,
)