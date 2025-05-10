package com.mhx.hotel.presentation.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.*
import com.mhx.hotel.data.model.Room
import com.mhx.hotel.data.model.RoomPic
import com.mhx.hotel.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class RoomsViewModel : ViewModel() {
    var rooms by mutableStateOf<List<Room>>(emptyList())
    var roomPics by mutableStateOf<List<RoomPic>>(emptyList())
    var errorMessage by mutableStateOf<String?>(null)

    fun loadData(token: String) {
        viewModelScope.launch {
            try {
                val roomRes = RetrofitClient.instance.getAllRooms(token)
                val picsRes = RetrofitClient.instance.getRoonsPics(token)

                if (roomRes.isSuccessful && picsRes.isSuccessful) {
                    rooms = roomRes.body() ?: emptyList()
                    roomPics = picsRes.body() ?: emptyList()
                } else {
                    errorMessage = "Error loading rooms or pics"
                }
            } catch (e: Exception) {
                errorMessage = e.message
            }
        }
    }

    fun getRoomImage(roomId: Int): String? {
        return roomPics.firstOrNull { it.id == roomId }?.image
    }
}
