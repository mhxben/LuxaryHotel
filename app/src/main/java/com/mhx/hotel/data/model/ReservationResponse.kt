package com.mhx.hotel.data.model

data class ReservationResponse(
    val client: Int,
    val end_date: String,
    val id: Int,
    val room: Int,
    val start_date: String,
    val status: String,
    val type: String
)