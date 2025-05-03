package com.mhx.hotel.presentation.view.component.rooms

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.mhx.hotel.data.model.Room
import com.mhx.hotel.data.remote.SharedPrefs
import com.mhx.hotel.presentation.navigation.NavigationActions
import com.mhx.hotel.presentation.viewmodel.GetHotelByIdViewModel
import com.mhx.hotel.presentation.viewmodel.GetRoomById

@Composable
fun RoomCard(room: Room, navController: NavController) {
    val context = LocalContext.current

    // ViewModel for room details and hotel info
    val roomViewModel: GetRoomById = viewModel()
    val hotelViewModel: GetHotelByIdViewModel = viewModel()

    // Fetch room details if not already fetched
    if (roomViewModel.room == null) {
        roomViewModel.getRoomById(room.id) // Fetch room info
    }

    // Fetch hotel details only once
    if (hotelViewModel.hotelInfo == null) {
        hotelViewModel.getHotelById(room.hotel) // Fetch hotel info
    }

    val roomPic = roomViewModel.roomImage
    val hotel = hotelViewModel.hotelInfo
    val hotelLocation = hotel?.address ?: "Location not available"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                SharedPrefs.saveRoomId(context, room.id)
                NavigationActions.navigationToBooking(navController)
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Display room picture if available
            roomPic?.let {
                Image(
                    painter = rememberImagePainter(it),
                    contentDescription = "Room Image",
                    modifier = Modifier
                        .size(200.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
            }

            // Room name, location, and price
            Text(room.name, style = MaterialTheme.typography.titleMedium)
            Text("Location: $hotelLocation")
            Text("Price: ${room.price}")
        }
    }
}
