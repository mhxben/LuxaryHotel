package com.mhx.hotel.presentation.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.*
import com.mhx.hotel.data.model.*
import com.mhx.hotel.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class GetRoomByIdViewModel : ViewModel() {
    var room by mutableStateOf<Room?>(null)
    var roomPic by mutableStateOf<RoomPic?>(null)
    var errorMessage by mutableStateOf<String?>(null)
    fun getRoomById(access : String , roomId : Int){
        viewModelScope.launch {
            val response = RetrofitClient.instance.getRoomById(access,roomId)
            val picture = RetrofitClient.instance.getRoomPicById(access,roomId)
            try {
                if (response.isSuccessful && picture.isSuccessful){
                    roomPic = picture.body()
                    room = response.body()
                }else{
                    errorMessage = response.errorBody().toString()
                }
            }catch ( e:Exception){
                errorMessage = e.message
            }
        }
    }
}