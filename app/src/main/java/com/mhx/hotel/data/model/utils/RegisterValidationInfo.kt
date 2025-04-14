package com.mhx.hotel.data.model.utils

import com.mhx.hotel.data.model.RegisterRequest

class RegisterValidationInfo {
    fun registervalidationInfo(request : RegisterRequest):String?{
        return when {
            request.fullName.isBlank() &&request.username.isBlank() && request.password.isBlank() ->{
                return "Please fill in the required fields"
            }
            request.fullName.isBlank() ->{
                return "Please enter your name"
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
            request.dateOfBirth.isBlank() ->{
                return "Please Enter your date of birth"
            }
            else -> null
        }
    }
}