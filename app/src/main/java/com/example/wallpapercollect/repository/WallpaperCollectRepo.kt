package com.example.wallpapercollect.repository

import com.example.wallpapercollect.api.models.Status
import com.example.wallpapercollect.api.models.UserLogIn
import com.example.wallpapercollect.api.models.UserRegister

interface WallpaperCollectRepo {
    suspend fun userRegister(user: UserRegister): Status
    suspend fun userLogin(user: UserLogIn): Status
    suspend fun userLogout()
}