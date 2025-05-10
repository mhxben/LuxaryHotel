package com.mhx.hotel.data.model

data class Review(
    val comment: String,
    val created_at: String,
    val id: Int,
    val rating: Int,
    val reviewer: Int,
    val room: Int
)