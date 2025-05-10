package com.mhx.hotel.presentation.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mhx.hotel.data.remote.SharedPrefs
import com.mhx.hotel.presentation.navigation.NavigationActions
import com.mhx.hotel.presentation.view.component.*
import com.mhx.hotel.presentation.view.component.home.*
import com.mhx.hotel.presentation.view.component.model.OutlinedTextFieldClass
import com.mhx.hotel.presentation.view.component.rooms.*
import com.mhx.hotel.presentation.viewmodel.GetUserViewModel
import com.mhx.hotel.ui.theme.DarkPrimary

@Composable
fun HomePage(navController: NavController){
    val context = LocalContext.current
    var search by remember { mutableStateOf("") }
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
    ){paddingValues ->
        StaticColumn(Modifier.padding(paddingValues)) {
            MainOutlinedTextField(
                params = OutlinedTextFieldClass(
                    label = "Search....",
                    value = search,
                    onValueChange = {search = it},
                    keyboardType = KeyboardType.Email)
            )
            RoomRowView(navController)
            CustomText("Popular place :" , DarkPrimary , 22,Modifier.align(Alignment.Start))
            RoomColumnView(navController)
        }
    }
}