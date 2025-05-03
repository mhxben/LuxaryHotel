package com.mhx.hotel.presentation.viewmodel

import android.content.Context
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhx.hotel.data.remote.RetrofitClient
import com.mhx.hotel.data.remote.SharedPrefs
import kotlinx.coroutines.launch

class LoginViewModel(private val context: Context) : ViewModel() {
    var userId by mutableStateOf<Int?>(null)
    var errorMessage by mutableStateOf<String?>(null)

    fun loginByUsername(usernameInput: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.loginAuth() // GET all users
                if (response.isSuccessful) {
                    val users = response.body() ?: emptyList()
                    val matchedUser = users.find { it.username == usernameInput }

                    if (matchedUser != null) {
                        userId = matchedUser.id
                        SharedPrefs.saveUserId(context, matchedUser.id)
                    } else {
                        errorMessage = "Username not found"
                    }
                } else {
                    errorMessage = response.errorBody()?.string()
                }
            } catch (e: Exception) {
                errorMessage = e.message
            }
        }
    }

}
