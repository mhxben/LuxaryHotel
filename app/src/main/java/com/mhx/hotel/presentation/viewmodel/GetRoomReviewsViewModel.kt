package com.mhx.hotel.presentation.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhx.hotel.data.model.Review
import com.mhx.hotel.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class GetRoomReviewsViewModel : ViewModel() {
    var reviews by mutableStateOf<List<Review>?>(null)
    var errorMessage by mutableStateOf<String?>(null)
    fun getRoomReviews(access : String , roomId :Int ) {
        viewModelScope.launch {
            val response = RetrofitClient.instance.getRoomReviews(access)
            try {
                if (response.isSuccessful){
                    reviews = response.body()?.filter { it.room == roomId }
                }else{
                    errorMessage = response.errorBody()?.string()
                }
            }catch (e: Exception){
                errorMessage = e.message
            }
        }
    }
}