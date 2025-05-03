package com.mhx.hotel.presentation.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.mhx.hotel.presentation.view.component.StaticColumn
import com.mhx.hotel.presentation.view.component.booking.RoomInfo
import com.mhx.hotel.presentation.view.component.booking.TopAppBar

@Composable
fun BookingPage(navController: NavController) {
    Scaffold(
        topBar = { TopAppBar(navController) }
    ) { padding ->
        StaticColumn(modifier = Modifier.padding(padding)) {
            RoomInfo()
        }
    }
}