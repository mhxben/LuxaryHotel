package com.mhx.hotel.data.model

data class Room(
    val created_at: String,
    val discount_used: String,
    val features: String,
    val hotel: Int,
    val id: Int,
    val name: String,
    val price: String,
    val room_number: String,
    val type: String
)