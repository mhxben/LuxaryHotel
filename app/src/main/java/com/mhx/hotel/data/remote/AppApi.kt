package com.mhx.hotel.data.remote

import com.mhx.hotel.data.model.*
import retrofit2.Response
import retrofit2.http.*

interface AppApi {
    @GET("users/users/")
    suspend fun loginAuth() : Response<List<UserAuth>>

    @POST("users/users/")
    suspend fun registerAut(@Body request: RegisterRequest) : Response<RegisterResponse>

    @GET("users/users/{id}")
    suspend fun getUserFromId(@Path("id") id:Int): Response<User>

    @GET("rooms/rooms/")
    suspend fun getRooms():Response<List<Room>>

    @GET("rooms/rooms/{id}")
    suspend fun getRoomById(@Path("id") id:Int):Response<Room>

    @GET("rooms/room-pictures/{id}")
    suspend fun getRoomPic(@Path("id") id : Int): Response<Picture>

    @GET("hotel/hotels/")
    suspend fun getHotels():Response<List<Hotel>>

    @GET("hotel/hotels/{id}")
    suspend fun getHotelById(@Path("id") id:Int):Response<Hotel>

    @POST("reservations/reservations/")
    suspend fun reservation(@Body request: ReservationRequest) : Response<ReservationResponse>

    @POST("reviews/reviews/")
    suspend fun review(@Body request: ReviewRequest) : Response<ReviewResponse>

    @POST("reviews/reviews/")
    suspend fun getAllReviews():Response<List<ReviewResponse>>
}