package com.mhx.hotel.presentation.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.gson.annotations.Until
import com.mhx.hotel.presentation.navigation.NavigationActions
import com.mhx.hotel.presentation.view.component.StaticColumn
import com.mhx.hotel.presentation.view.component.home.AppButtonBar
import com.mhx.hotel.presentation.view.component.home.AppTopBar
import com.mhx.hotel.presentation.viewmodel.GetUserByIdViewModel

@Composable
fun HomePage(navController: NavController , userId: String){
    val viewModel : GetUserByIdViewModel = viewModel()
    LaunchedEffect(Unit){
        viewModel.getUserById(userId)
    }
    Scaffold(
        topBar = {
            AppTopBar(
                fullName = viewModel.user?.fullname ?: "Loading....",
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