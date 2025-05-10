package com.mhx.hotel.presentation.view.component.rooms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mhx.hotel.data.model.Hotel
import com.mhx.hotel.data.model.Room
import com.mhx.hotel.data.remote.RetrofitClient
import com.mhx.hotel.data.remote.SharedPrefs
import com.mhx.hotel.presentation.viewmodel.RoomsViewModel

@Composable
fun RoomColumnView(navController: NavController) {
    val viewModel : RoomsViewModel = viewModel()
    val context = LocalContext.current
    val access = SharedPrefs.getToken(context)
    LaunchedEffect(Unit) {
        access?.let { token ->
            viewModel.loadData(token)
        }
    }
    if(viewModel.rooms.isEmpty()){
        Text("No rooms available.")
    }else{
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(horizontal = 16.dp)
        ) {
            items(viewModel.rooms) { room ->
                val imageUrl = viewModel.roomPics.firstOrNull { it.id == room.id }?.image ?: ""
                var hotel by remember { mutableStateOf<Hotel?>(null) }

                LaunchedEffect(room.hotel) {
                    access?.let { token ->
                        val res = RetrofitClient.instance.getHotelById(token, room.hotel)
                        if (res.isSuccessful) hotel = res.body()
                    }
                }
                hotel?.let {
                    RoomCard(
                        roomId = room.id,
                        roomName = room.name,
                        location = it.address,
                        owner = it.name,
                        price = room.price,
                        imageUrl = imageUrl,
                        navController = navController
                    )
                }
            }
        }
    }
}