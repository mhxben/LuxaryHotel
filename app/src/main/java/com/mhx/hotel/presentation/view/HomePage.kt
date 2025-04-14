package com.mhx.hotel.presentation.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.mhx.hotel.presentation.navigation.NavigationActions
import com.mhx.hotel.presentation.view.component.StaticColumn
import com.mhx.hotel.presentation.view.component.home.AppButtonBar
import com.mhx.hotel.presentation.view.component.home.AppTopBar

@Composable
fun HomePage(navController: NavController){
    Scaffold(
        topBar = {
            AppTopBar(
                fullName = "Mohamed Ali",
                onLogout = {NavigationActions.navigationToLogin(navController)}
            )
        },
        bottomBar = {
            AppButtonBar(navController)
        }
    ){paddingValues ->
        StaticColumn(Modifier.padding(paddingValues)) {

        }
    }
}