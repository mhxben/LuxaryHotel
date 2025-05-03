package com.mhx.hotel.data.model

data class ReservationRequest(
    val client: Int,
    val end_date: String,
    val room: Int,
    val start_date: String,
    val status: String,
    val type: String
)