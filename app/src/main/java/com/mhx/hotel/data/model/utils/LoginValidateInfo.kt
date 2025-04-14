package com.mhx.hotel.data.model.utils

import com.mhx.hotel.data.model.LoginRequest

class LoginValidateInfo {
    fun validateLoginInfos(request : LoginRequest) : String?{
        return when {
            request.username.isBlank() && request.password.isBlank() ->{
                "Please enter your information"
            }
            request.username.isBlank() ->{
                "Please enter your username"
            }

            request.password.isBlank() ->{
                "Please enter your password"
            }
            request.password.length < 6 ->{
                "Your password length is too short"
            }
            else -> null
        }
    }
}