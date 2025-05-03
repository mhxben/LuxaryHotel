package com.mhx.hotel.data.model

data class ReviewRequest(
    val comment: String,
    val rating: Int,
    val reviewer: Int,
    val room: Int
)