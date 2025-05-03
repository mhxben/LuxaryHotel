package com.mhx.hotel.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhx.hotel.data.model.Hotel
import com.mhx.hotel.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class GetHotelByIdViewModel : ViewModel() {
    var hotelInfo by mutableStateOf<Hotel?>(null)
    var error by mutableStateOf<String?>(null)
    fun getHotelById(id : Int) {
        viewModelScope.launch {
            val response = RetrofitClient.instance.getHotelById(id)
            try {
                if (response.isSuccessful) {
                    hotelInfo = response.body()
                } else {
                    error = response.errorBody()?.string()
                }
            }catch (e: Exception) {
                error = e.message
            }
        }
    }
}