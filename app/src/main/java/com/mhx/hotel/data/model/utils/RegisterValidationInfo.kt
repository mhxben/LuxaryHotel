package com.mhx.hotel.data.model.utils

import com.mhx.hotel.data.model.RegisterRequest

class RegisterValidationInfo {
    fun registervalidationInfo(request : RegisterRequest):String?{
        return when {
            request.email.isBlank() &&request.username.isBlank() && request.password.isBlank() ->{
                return "Please fill in the required fields"
            }
            request.email.isBlank()->{
                return "Please enter a valid email"
            }
            request.username.isBlank()->{
                return "Please enter a valid username"
            }
            request.password.isBlank()->{
                return "Please enter your password"
            }
            request.password.length <6 ->{
                return "Your password is too short"
            }
            else -> null
        }
    }
}