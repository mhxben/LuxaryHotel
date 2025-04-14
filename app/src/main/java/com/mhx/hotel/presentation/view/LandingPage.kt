package com.mhx.hotel.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mhx.hotel.R
import com.mhx.hotel.presentation.navigation.NavigationActions
import com.mhx.hotel.ui.theme.DarkPrimary
import kotlinx.coroutines.delay

@Composable
fun LandingPage(navController: NavController){
    Box(
        Modifier
            .fillMaxSize()
            .background(DarkPrimary),
        contentAlignment = Alignment.Center
    ){
        LaunchedEffect(Unit){
            delay(2000)
            NavigationActions.navigationToLogin(navController)
        }
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "App Logo"
            ,Modifier.size(300.dp)
        )
    }
}