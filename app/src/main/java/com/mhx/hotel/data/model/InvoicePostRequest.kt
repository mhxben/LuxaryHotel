package com.mhx.hotel.data.model

data class InvoicePostRequest(
    val amount: String,
    val client: Int,
    val discount_code: String,
    val services_used: String,
    val status: String
)