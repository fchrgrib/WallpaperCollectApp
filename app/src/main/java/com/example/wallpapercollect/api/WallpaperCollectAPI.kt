package com.example.wallpapercollect.api

import com.example.wallpapercollect.api.ApiEndPoints
import com.example.wallpapercollect.api.models.Status
import com.example.wallpapercollect.api.models.UserRegister
import com.example.wallpapercollect.api.models.UserLogIn
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface WallpaperCollectAPI {
    @POST(ApiEndPoints.REGISTER)
    suspend fun userRegister(
        @Body user: UserRegister
    ): Status

    @POST(ApiEndPoints.LOGIN)
    suspend fun userLogin(
        @Body user: UserLogIn
    ): Status

    @GET(ApiEndPoints.LOGOUT)
    suspend fun userLogout()


}