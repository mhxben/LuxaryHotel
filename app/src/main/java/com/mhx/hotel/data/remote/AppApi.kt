package com.mhx.hotel.data.remote

import com.mhx.hotel.data.model.*
import retrofit2.Response
import retrofit2.http.*

interface AppApi {
    @Headers("Accept: application/json")
    @POST("token/")
    suspend fun loginAuth(@Body request: LoginRequest): Response<LoginResponse>

    @Headers("Accept: application/json")
    @GET("users/me/")
    suspend fun getUser(@Header("Authorization") access: String): Response<User>

    @Headers("Accept: application/json")
    @POST("users/register/")
    suspend fun registerAuth(@Body request: RegisterRequest): Response<RegisterResponse>

    @Headers("Accept: application/json")
    @GET("rooms/rooms")
    suspend fun getAllRooms(@Header("Authorization") access: String): Response<List<Room>>

    @GET("rooms/room-pictures/")
    suspend fun getRoonsPics(@Header("Authorization") access: String): Response<List<RoomPic>>

    @GET("hotel/hotels/{id}")
    suspend fun getHotelById(@Header("Authorization") access: String , @Path ("id") id :Int):Response<Hotel>

    @GET("rooms/rooms/{id}")
    suspend fun getRoomById(@Header("Authorization") access: String , @Path("id") id:Int):Response<Room>

    @GET("rooms/room-pictures/{id}")
    suspend fun getRoomPicById(@Header("Authorization") access: String , @Path("id") id:Int): Response<RoomPic>

    @GET("reviews/reviews")
    suspend fun getRoomReviews(@Header("Authorization") access: String ): Response<List<Review>>

    @POST("reservations/reservations/")
    suspend fun addReservation(@Header("Authorization") access : String , @Body request: ReservationRequest) : Response<ReservationResponse>
}