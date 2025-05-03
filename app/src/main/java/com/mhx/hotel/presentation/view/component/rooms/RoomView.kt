package com.mhx.hotel.presentation.view.component.rooms

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mhx.hotel.presentation.viewmodel.GetRoomsViewModel

@Composable
fun RoomView(navController: NavController) {
    val viewModel: GetRoomsViewModel = viewModel()
    val rooms = viewModel.rooms
    val error = viewModel.error

    LaunchedEffect(Unit) {
        viewModel.getRooms()
    }

    when {
        error != null -> {
            Text(
                text = "Error: $error",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(16.dp)
            )
        }
        rooms.isNullOrEmpty() -> {
            Text("No rooms available", modifier = Modifier.padding(16.dp))
        }
        else -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(8.dp)
            ) {
                items(rooms ?: emptyList()) { room ->
                    RoomCard(room = room , navController)
                }
            }
        }
    }
}
