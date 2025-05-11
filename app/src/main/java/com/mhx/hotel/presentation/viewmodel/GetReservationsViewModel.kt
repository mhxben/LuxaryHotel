package com.mhx.hotel.presentation.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.*
import com.mhx.hotel.data.model.ReservationResponse
import com.mhx.hotel.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class GetReservationsViewModel : ViewModel() {
    var reservation by mutableStateOf<List<ReservationResponse>?>(null)
    var errorMessage by mutableStateOf<String?>(null)
    fun getReservations(access : String , userId : Int) {
        viewModelScope.launch {
            val response = RetrofitClient.instance.getReservation(access)
            try {
                if(response.isSuccessful){
                    reservation = response.body()?.filter { it.client == userId }
                }else{
                    errorMessage = "Failed to fetch data from server"
                }
            }catch ( e : Exception){
                errorMessage = e.message
            }
        }
    }
}