package com.mhx.hotel.data.model

data class InvoicePostResponse(
    val amount: String,
    val client: Int,
    val created_at: String,
    val discount_code: String,
    val id: Int,
    val services_used: String,
    val status: String
)