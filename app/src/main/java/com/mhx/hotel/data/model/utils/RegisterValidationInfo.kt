package com.mhx.hotel.data.model.utils

import com.mhx.hotel.data.model.RegisterRequest

class RegisterValidationInfo {
    fun registerValidationInfo(request: RegisterRequest): String? {
        return when {
            request.email.isBlank() || request.username.isBlank() || request.password.isBlank()
                    || request.confirm_password.isBlank() || request.first_name.isBlank()
                    || request.last_name.isBlank() || request.role.isBlank() -> {
                "Please fill in all the required fields"
            }

            !android.util.Patterns.EMAIL_ADDRESS.matcher(request.email).matches() -> {
                "Invalid email format"
            }

            request.username.length < 3 -> {
                "Username must be at least 3 characters"
            }

            request.first_name.length < 2 -> {
                "First name must be at least 2 characters"
            }

            request.last_name.length < 2 -> {
                "Last name must be at least 2 characters"
            }

            request.password.length < 6 -> {
                "Password must be at least 6 characters"
            }

            request.password != request.confirm_password -> {
                "Passwords do not match"
            }

            else -> null
        }
    }
}
