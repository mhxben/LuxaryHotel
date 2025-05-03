package com.mhx.hotel.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhx.hotel.data.model.ReviewResponse
import com.mhx.hotel.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class GetAllReviewsViewModel : ViewModel() {
    var allReviews by mutableStateOf<List<ReviewResponse>?>(null)
    var errorMessage by mutableStateOf<String?>(null)
    fun getAllReviews() {
        viewModelScope.launch {
            val response = RetrofitClient.instance.getAllReviews()
            try{
                if (response.isSuccessful) {
                    allReviews = response.body()
                }else{
                    errorMessage = response.errorBody().toString()
                }
            }catch (e: Exception){
                errorMessage = e.message
            }
        }
    }
}