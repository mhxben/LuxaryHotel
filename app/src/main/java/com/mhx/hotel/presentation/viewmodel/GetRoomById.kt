package com.mhx.hotel.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhx.hotel.data.model.Picture
import com.mhx.hotel.data.model.Room
import com.mhx.hotel.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class GetRoomById : ViewModel() {
    var room by mutableStateOf<Room?>(null)
    var roomImage by mutableStateOf<Picture?>(null)
    var errorMessage by mutableStateOf<String?>(null)

    fun getRoomById(id: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.getRoomById(id)
                if (response.isSuccessful) {
                    room = response.body()
                    getRoomImage(id) // Fetch room image after fetching room details
                } else {
                    errorMessage = response.errorBody()?.string()
                }
            } catch (e: Exception) {
                errorMessage = e.message
            }
        }
    }

    private suspend fun getRoomImage(id: Int) {
        try {
            val response = RetrofitClient.instance.getRoomPic(id)
            if (response.isSuccessful) {
                roomImage = response.body()
            } else {
                errorMessage = response.errorBody()?.string()
            }
        } catch (e: Exception) {
            errorMessage = e.message
        }
    }
}
