package com.mhx.hotel.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhx.hotel.data.model.Hotel
import com.mhx.hotel.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class GetHotelsViewModel : ViewModel()  {
    var hotels by mutableStateOf<List<Hotel>?>(null)
    var errorMesage by mutableStateOf<String?>(null)
    fun getHotels(){
        viewModelScope.launch {
            val response = RetrofitClient.instance.getHotels()
            try {
                if(response.isSuccessful){
                    hotels = response.body()
                }else{
                    errorMesage = response.errorBody()?.string()
                }
            }catch (e: Exception){
                errorMesage = e.message
            }
        }
    }
}