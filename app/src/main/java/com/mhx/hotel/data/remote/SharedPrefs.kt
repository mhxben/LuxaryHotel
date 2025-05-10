package com.mhx.hotel.data.remote

import android.content.Context

object SharedPrefs {

    private const val PREFS_NAME = "auth_prefs"
    private const val TOKEN_KEY = "token"
    private const val USER_ID_KEY = "user_id"
    private const val ROOM_ID_KEY = "room_id"
    private const val HOTEL_ID_KEY = "hotel_id"

    private fun prefs(context: Context) =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    // Token
    fun saveToken(context: Context, token: String) {
        prefs(context).edit().putString(TOKEN_KEY, token).apply()
    }

    fun getToken(context: Context): String? {
        return prefs(context).getString(TOKEN_KEY, null)
    }

    fun removeToken(context: Context) {
        prefs(context).edit().remove(TOKEN_KEY).apply()
    }

    // User ID
    fun saveUserId(context: Context, userId: Int) {
        prefs(context).edit().putInt(USER_ID_KEY, userId).apply()
    }

    fun getUserId(context: Context): Int? {
        val id = prefs(context).getInt(USER_ID_KEY, -1)
        return if (id != -1) id else null
    }

    fun removeUserId(context: Context) {
        prefs(context).edit().remove(USER_ID_KEY).apply()
    }

    // Room ID
    fun saveRoomId(context: Context, roomId: Int) {
        prefs(context).edit().putInt(ROOM_ID_KEY, roomId).apply()
    }

    fun getRoomId(context: Context): Int? {
        val id = prefs(context).getInt(ROOM_ID_KEY, -1)
        return if (id != -1) id else null
    }

    fun removeRoomId(context: Context) {
        prefs(context).edit().remove(ROOM_ID_KEY).apply()
    }

    // Hotel ID
    fun saveHotelId(context: Context, hotelId: Int) {
        prefs(context).edit().putInt(HOTEL_ID_KEY, hotelId).apply()
    }

    fun getHotelId(context: Context): Int? {
        val id = prefs(context).getInt(HOTEL_ID_KEY, -1)
        return if (id != -1) id else null
    }

    fun removeHotelId(context: Context) {
        prefs(context).edit().remove(HOTEL_ID_KEY).apply()
    }
}
