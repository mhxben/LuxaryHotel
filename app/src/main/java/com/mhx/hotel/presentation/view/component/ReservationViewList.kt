package com.mhx.hotel.presentation.view.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mhx.hotel.data.remote.RetrofitClient
import com.mhx.hotel.data.remote.SharedPrefs
import com.mhx.hotel.presentation.navigation.NavigationActions
import com.mhx.hotel.presentation.viewmodel.GetReservationsViewModel

@Composable
fun ReservationViewList(navController: NavController,modifier: Modifier = Modifier){
    val viewModel : GetReservationsViewModel = viewModel()
    val context = LocalContext.current
    val access = SharedPrefs.getToken(context)
    val userId = SharedPrefs.getUserId(context)
    LaunchedEffect(true){
        if(access != null && userId != null){
            viewModel.getReservations(access, userId)
        }
    }
    LazyColumn(modifier.fillMaxSize()) {
        items(viewModel.reservation ?: emptyList()){ reservation ->
            val roomId = reservation.room
            val roomImage = remember { mutableStateOf<String?>(null) }
            val roomName = remember { mutableStateOf<String?>(null) }
            LaunchedEffect(roomId){
                access?.let{
                    val imgResponse = RetrofitClient.instance.getRoomPicById(access,roomId)
                    if (imgResponse.isSuccessful) {
                        roomImage.value = imgResponse.body()?.image
                    }
                    val roomNameResponse = RetrofitClient.instance.getRoomById(access ,roomId)
                    if (roomNameResponse.isSuccessful) {
                        roomName.value = roomNameResponse.body()?.name
                    }
                }
            }
            ReservationComponent(
                reservationId = reservation.id,
                imageUrl = roomImage.value ?: "",
                startDate = reservation.start_date,
                endDate = reservation.end_date,
                state = reservation.status,
                roomName = roomName.value ?:"Loading",
                onClick = {NavigationActions.navigationToPaymentPage(navController = navController)}
            )
        }
    }
}