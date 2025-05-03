package com.mhx.hotel.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhx.hotel.data.model.ReservationRequest
import com.mhx.hotel.data.model.ReservationResponse
import com.mhx.hotel.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class ReservationViewModel : ViewModel() {
    var reservationResponse by mutableStateOf<ReservationResponse?>(null)
    var errorMessage by mutableStateOf<String?>(null)
    fun postReservation(request : ReservationRequest) {
        viewModelScope.launch{
            val response = RetrofitClient.instance.reservation(request)
            try{
                if (response.isSuccessful) {
                    reservationResponse = response.body()
                }else{
                    errorMessage = response.errorBody()?.string()
                }
            }catch (e: Exception){
                errorMessage = e.localizedMessage
            }

        }
    }
}