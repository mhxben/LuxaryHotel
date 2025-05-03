package com.mhx.hotel.presentation.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.*
import com.mhx.hotel.data.model.*
import com.mhx.hotel.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class ReviewViewModel : ViewModel() {
    var postReview by mutableStateOf<ReviewResponse?>(null)
    var errorMessage by mutableStateOf<String?>(null)
    fun addReview(request: ReviewRequest){
        viewModelScope.launch {
            val response = RetrofitClient.instance.review(request)
            try {
                if (response.isSuccessful) {
                    postReview = response.body()
                }else{
                    errorMessage = response.errorBody().toString()
                }
            }catch (e: Exception){
                errorMessage = e.message
            }
        }
    }
}