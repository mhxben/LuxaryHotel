package com.mhx.hotel.presentation.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.*
import com.mhx.hotel.data.model.*
import com.mhx.hotel.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    var registerResponse by mutableStateOf<RegisterResponse?>(null)
    var errorMessage by mutableStateOf<String?>(null)

    fun register(request: RegisterRequest){
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.registerAut(request)
                if (response.isSuccessful){
                    registerResponse = response.body()
                }else{
                    errorMessage = response.errorBody()?.string()
                }
            }catch (e: Exception){
                errorMessage = e.message
            }
        }
    }
}