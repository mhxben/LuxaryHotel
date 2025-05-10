package com.mhx.hotel.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mhx.hotel.data.remote.SharedPrefs
import com.mhx.hotel.presentation.navigation.NavigationActions
import com.mhx.hotel.presentation.view.component.AppButton
import com.mhx.hotel.presentation.view.component.StaticColumn
import com.mhx.hotel.presentation.view.component.booking.TopAppBar
import com.mhx.hotel.presentation.view.component.rooms.RoomPresentation
import com.mhx.hotel.presentation.viewmodel.GetRoomByIdViewModel

@Composable
fun BookingPage(navController: NavController) {
    val viewModel :GetRoomByIdViewModel = viewModel()
    val context = LocalContext.current
    val access = SharedPrefs.getToken(context)
    val roomId = SharedPrefs.getRoomId(context)
    LaunchedEffect(roomId){
        if(access != null && roomId != null){
            viewModel.getRoomById(access,roomId)
        }
    }
    Scaffold(
        topBar = { TopAppBar(viewModel.room?.name ?:"Loading",navController) },
        bottomBar = { AppButton("Reserve now!", onClick = {NavigationActions.navigationToReservationForm(navController)},) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            RoomPresentation(
                imageUrl = viewModel.roomPic?.image ?: "",
                roomName = viewModel.room?.name ?: "Room name",
                location = "Unknown location",
                price = viewModel.room?.price ?: "0 DA"
            )
        }
    }
}