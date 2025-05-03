package com.mhx.hotel.data.model

data class Hotel(
    val address: String,
    val city: String,
    val country: String,
    val email: String,
    val id: Int,
    val name: String,
    val phone_number: String,
    val pictures: List<Picture>
)