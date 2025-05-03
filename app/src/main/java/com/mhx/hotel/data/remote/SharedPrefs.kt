package com.mhx.hotel.data.remote

import android.content.Context

object SharedPrefs {

    private const val PREFS_NAME = "auth_prefs"
    private const val TOKEN_KEY = "token"
    private const val USER_ID_KEY = "user_id"
    private const val ROOM_ID_KEY = "room_id"
    private const val HOTEL_ID_KEY = "hotel_id"  // Added for hotel ID

    // Save Token
    fun saveToken(context: Context, token: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(TOKEN_KEY, token).apply()
    }

    // Get Token
    fun getToken(context: Context): String? {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(TOKEN_KEY, null)
    }

    // Clear Token
    fun clearToken(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().remove(TOKEN_KEY).apply()
    }

    // Save User ID
    fun saveUserId(context: Context, userId: Int) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putInt(USER_ID_KEY, userId).apply()
    }

    // Get User ID
    fun getUserId(context: Context): Int? {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val id = prefs.getInt(USER_ID_KEY, -1)
        return if (id != -1) id else null
    }

    // Clear User ID
    fun clearUserId(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().remove(USER_ID_KEY).apply()
    }

    // Save Room ID
    fun saveRoomId(context: Context, roomId: Int) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putInt(ROOM_ID_KEY, roomId).apply()
    }

    // Get Room ID
    fun getRoomId(context: Context): Int {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getInt(ROOM_ID_KEY, -1) // Returns -1 if not found
    }

    // Clear Room ID
    fun clearRoomId(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().remove(ROOM_ID_KEY).apply()
    }

    // Save Hotel ID
    fun saveHotelId(context: Context, hotelId: Int) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putInt(HOTEL_ID_KEY, hotelId).apply()
    }

    // Get Hotel ID
    fun getHotelId(context: Context): Int {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getInt(HOTEL_ID_KEY, -1) // Returns -1 if not found
    }

    // Clear Hotel ID
    fun clearHotelId(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().remove(HOTEL_ID_KEY).apply()
    }
}
