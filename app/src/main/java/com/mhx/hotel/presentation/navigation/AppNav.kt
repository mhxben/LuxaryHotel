package com.mhx.hotel.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mhx.hotel.presentation.view.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController , NavigationRoutes.Landing){
        composable(NavigationRoutes.Landing) { LandingPage(navController) }
        composable(NavigationRoutes.Login) { LoginPage(navController) }
        composable(NavigationRoutes.SignUp) { SignUpPage(navController) }
        composable(NavigationRoutes.Home) { HomePage(navController) }
        composable(NavigationRoutes.Booking) { BookingPage(navController) }
        composable(NavigationRoutes.Reservation) { ReservationFormulaireView(navController) }
    }
}