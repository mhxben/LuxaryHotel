package com.mhx.hotel.presentation.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.*
import com.mhx.hotel.data.model.Room
import com.mhx.hotel.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class GetRoomsViewModel : ViewModel() {
    var rooms by mutableStateOf<List<Room>?>(null)
    var error by mutableStateOf<String?>(null)
    fun getRooms (){
        viewModelScope.launch {
            val response = RetrofitClient.instance.getRooms()
            try {
                if(response.isSuccessful){
                    rooms = response.body()
                }else{
                    error = response.errorBody()?.string()
                }

            }catch (e:Exception){
                error = e.message
            }
        }
    }
}