package com.mhx.hotel.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhx.hotel.data.model.LoginRequest
import com.mhx.hotel.data.model.LoginResponse
import com.mhx.hotel.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    var loginResponse by mutableStateOf<LoginResponse?>(null)
    var errorMessage by mutableStateOf<String?>(null)

    fun login(request: LoginRequest){
        viewModelScope.launch {
            try{
                val response = RetrofitClient.instance.loginAuth(request)
                if (response.isSuccessful){
                    loginResponse = response.body()
                }else{
                    errorMessage = response.errorBody()?.string()
                }
            }catch (e:Exception){
                errorMessage = e.message
            }
        }
    }
}