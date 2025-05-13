package com.mhx.hotel.presentation.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mhx.hotel.data.remote.SharedPrefs
import com.mhx.hotel.presentation.navigation.NavigationActions
import com.mhx.hotel.presentation.view.component.ReservationViewList
import com.mhx.hotel.presentation.view.component.home.*
import com.mhx.hotel.presentation.viewmodel.GetUserViewModel

@Composable
fun ReservationListView(navController : NavController){
    val context = LocalContext.current
    val access = SharedPrefs.getToken(context)
    val viewModel : GetUserViewModel = viewModel()
    LaunchedEffect(access) {
        access?.let {
            viewModel.getUser(it)
            viewModel.user?.id?.let { userId ->
                SharedPrefs.saveUserId(context, userId)
            }
        }
    }
    Scaffold(
        topBar = {
            AppTopBar(
                fullName = viewModel.user?.first_name ?: if (viewModel.errorMessage != null) "Error loading name" else "Loading..." ,
                onLogout = {
                    NavigationActions.navigationToLogin(navController)
                    SharedPrefs.removeToken(context)
                }
            )
        },
        bottomBar = {
            AppButtonBar(navController)
        }
    ) { padding ->
        ReservationViewList(navController,modifier = Modifier.padding(padding))
    }

}