package com.mhx.hotel.presentation.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhx.hotel.data.model.RoomPic
import com.mhx.hotel.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class RoomsPicViewModel : ViewModel() {
    var roomPics by mutableStateOf<List<RoomPic>?>(null)
    var errorMessage by mutableStateOf<String?>(null)
    fun getRoomsPic(access : String){
        viewModelScope.launch {
            val response = RetrofitClient.instance.getRoonsPics(access)
            try {
                if(response.isSuccessful){
                    roomPics = response.body()
                }else{
                    errorMessage = response.errorBody().toString()
                }
            }catch (e: Exception){
                errorMessage = e.message
            }
        }
    }
}