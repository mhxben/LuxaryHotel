package com.mhx.hotel.presentation.viewmodel

import android.content.Context
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhx.hotel.data.model.*
import com.mhx.hotel.data.remote.*
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel(){
    var loginResponse by mutableStateOf<LoginResponse?>(null)
    var errorMessage by mutableStateOf<String?>(null)
    fun login (context : Context, request : LoginRequest) {
        viewModelScope.launch {
            val response = RetrofitClient.instance.loginAuth(request)
            try {
                if(response.isSuccessful){
                    loginResponse = response.body()
                    val token = loginResponse?.access ?:return@launch
                    val bearerToken = "Bearer $token"
                    SharedPrefs.saveToken(context,bearerToken)
                }else{
                    errorMessage = response.errorBody()?.string() ?: "Login failed"
                }
            }catch (e : Exception){
                errorMessage = e.message ?: "Unexpected error"
            }
        }
    }
}