package com.mhx.hotel.presentation.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhx.hotel.data.model.User
import com.mhx.hotel.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class GetUserViewModel : ViewModel() {
    var user by mutableStateOf<User?>(null)
    var errorMessage by mutableStateOf<String?>(null)

    fun getUser(accessToken: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.getUser(accessToken)
                if (response.isSuccessful) {
                    user = response.body()
                } else {
                    errorMessage = response.errorBody()?.string() ?: "Unauthorized"
                }
            } catch (e: Exception) {
                errorMessage = e.message ?: "Network error"
            }
        }
    }
}
