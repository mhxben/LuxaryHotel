package com.mhx.hotel.presentation.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mhx.hotel.data.remote.SharedPrefs
import com.mhx.hotel.presentation.navigation.NavigationActions
import com.mhx.hotel.presentation.view.component.MainOutlinedTextField
import com.mhx.hotel.presentation.view.component.StaticColumn
import com.mhx.hotel.presentation.view.component.home.AppButtonBar
import com.mhx.hotel.presentation.view.component.home.AppTopBar
import com.mhx.hotel.presentation.view.component.model.OutlinedTextFieldClass
import com.mhx.hotel.presentation.view.component.rooms.RoomView
import com.mhx.hotel.presentation.viewmodel.GetUserByIdViewModel

@Composable
fun HomePage(navController: NavController){
    val context = LocalContext.current
    var search by remember { mutableStateOf("") }
    val userId = SharedPrefs.getUserId(context)
    val viewModel : GetUserByIdViewModel = viewModel()
    if (userId == null) {
        NavigationActions.navigationToLogin(navController)
    }
    LaunchedEffect(userId) {
        userId?.let { viewModel.getUserById(it) }
    }
    Scaffold(
        topBar = {
            AppTopBar(
                fullName = viewModel.user?.username ?:"Loading",
                onLogout = {
                    SharedPrefs.clearToken(context)
                    NavigationActions.navigationToLogin(navController)
                }
            )
        },
        bottomBar = {
            AppButtonBar(navController)
        }
    ){paddingValues ->
        StaticColumn(Modifier.padding(paddingValues)) {
            MainOutlinedTextField(
                params = OutlinedTextFieldClass(
                    label = "Search....",
                    value = search,
                    onValueChange = {search = it},
                    keyboardType = KeyboardType.Email)
            )
            RoomView(navController)
        }
    }
}