package com.mhx.hotel.data.remote

import com.mhx.hotel.data.model.*
import retrofit2.Response
import retrofit2.http.*

interface AppApi {
    @POST("users/login")
    suspend fun loginAuth(@Body request : LoginRequest) : Response<LoginResponse>
    @POST("users/register")
    suspend fun registerAut(@Body request: RegisterRequest) : Response<RegisterResponse>
    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id:String): Response<User>
}