package com.mhx.hotel.data.model

data class ReviewResponse(
    val comment: String,
    val created_at: String,
    val id: Int,
    val rating: Int,
    val reviewer: Int,
    val room: Int
)