package com.mhx.hotel.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mhx.hotel.data.model.InvoicePostRequest
import com.mhx.hotel.data.model.InvoicePostResponse
import com.mhx.hotel.data.remote.RetrofitClient
import kotlinx.coroutines.launch

class InvoicePostViewModel : ViewModel() {
    var invoicePostResponse by mutableStateOf<InvoicePostResponse?>(null)
    var errorMessage by mutableStateOf<String?>(null)
    fun postInvoice(access : String , postRequest: InvoicePostRequest) {
        viewModelScope.launch {
            val response = RetrofitClient.instance.postInvoice(access , postRequest)
            try {
                if (response.isSuccessful){
                    invoicePostResponse = response.body()
                }else{
                    errorMessage = response.errorBody().toString()
                }
            }catch ( e : Exception){
                errorMessage = e.message
            }
        }
    }
}