package com.mhx.hotel.presentation.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhx.hotel.data.model.ReservationResponse
import com.mhx.hotel.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class GetReservationByIdViewModel : ViewModel() {
    var reservation by mutableStateOf<ReservationResponse?>(null)
    var errorMessage by mutableStateOf<String?>(null)
    fun getReservation(access : String,id: Int) {
        viewModelScope.launch {
            val response = RetrofitClient.instance.getReservationById(access,id)
            try{
                if (response.isSuccessful){
                    reservation = response.body()
                }else{
                    errorMessage = response.errorBody().toString()
                }
            }catch (e : Exception){
                errorMessage = e.message
            }
        }
    }
}