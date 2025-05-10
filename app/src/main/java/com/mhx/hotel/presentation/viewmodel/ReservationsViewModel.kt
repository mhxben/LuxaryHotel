package com.mhx.hotel.presentation.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.*
import com.mhx.hotel.data.model.*
import com.mhx.hotel.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class ReservationsViewModel : ViewModel() {
    var reservationResponse by mutableStateOf<ReservationResponse?>(null)
    var errorMessage by mutableStateOf<String?>(null)
    fun postReservation(access : String ,request: ReservationRequest){
        viewModelScope.launch {
            val response = RetrofitClient.instance.addReservation(access,request)
            try {
                if(response.isSuccessful){
                    reservationResponse = response.body()
                }else{
                    errorMessage = response.errorBody().toString()
                }
            }catch (e: Exception){
                errorMessage = e.message
            }
        }
    }
}