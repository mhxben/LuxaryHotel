package com.mhx.hotel.presentation.navigation

object NavigationRoutes {
    const val Landing = "landing"
    const val Login = "login"
    const val SignUp = "signUp"
    const val Home = "home/{userId}"
    const val Booking = "booking"

    fun HomeWithId(userId: Int) = "home/$userId"
}