package com.mhx.hotel.presentation.navigation

import androidx.navigation.NavController

object NavigationActions {
    fun navigationToLogin(navController: NavController){
        navController.navigate(NavigationRoutes.Login){
            popUpTo(NavigationRoutes.Landing) { inclusive = true }
        }
    }
    fun navigationToSignUp(navController: NavController){
        navController.navigate(NavigationRoutes.SignUp)
    }
    fun navigationToHome(navController: NavController){
        navController.navigate(NavigationRoutes.Home)
    }
}