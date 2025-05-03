package com.mhx.hotel.presentation.view.component.booking

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.mhx.hotel.data.remote.SharedPrefs
import com.mhx.hotel.presentation.view.component.AppButton
import com.mhx.hotel.presentation.view.component.StaticColumn
import com.mhx.hotel.presentation.viewmodel.GetRoomById
import com.mhx.hotel.ui.theme.AccentYellow

@Composable
fun RoomInfo() {
    val context = LocalContext.current
    val viewModel: GetRoomById = viewModel()

    // Get the room ID from SharedPrefs
    val roomId = SharedPrefs.getRoomId(context)

    // Fetch room data using the room ID
    LaunchedEffect(roomId) {
        if (roomId != -1) {
            viewModel.getRoomById(roomId)
        }
    }

    // Get the room data from the ViewModel
    val room = viewModel.room
    val roomImage = viewModel.roomImage
    val error = viewModel.errorMessage

    StaticColumn(modifier = Modifier.padding(16.dp)) {
        if (room != null) {
            // Display room image with rounded corners
            roomImage?.let {
                Image(
                    painter = rememberImagePainter(it.image),
                    contentDescription = "Room Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(bottom = 16.dp)
                        .clip(RoundedCornerShape(16.dp)) // Rounded corners
                )
            }

            // Display room name and price in a row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Room: ${room.name}", style = MaterialTheme.typography.titleLarge)
                Text(text = "Price: \$${room.price}", style = MaterialTheme.typography.bodyLarge)
            }

            // Display room features
            Column(modifier = Modifier.padding(bottom = 16.dp)) {
                Text(text = "Features: ${room.features}")
                Text(text = "Discount used: ${room.discount_used}")
            }

            // Display reserve button
            AppButton(
                text = "Reserve Now",
                onClick = {
                    // Add your reservation logic here, for example, saving the reservation or navigating
                    // For now, we will just clear the button's state (let it blank after click)
                }
            )
        } else if (error != null) {
            Text(text = "Error: $error", color = AccentYellow)
        } else {
            Text(text = "Loading room details...")
        }
    }
}
