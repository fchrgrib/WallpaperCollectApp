package com.example.wallpapercollect.repository

import com.example.wallpapercollect.api.WallpaperCollectAPI
import com.example.wallpapercollect.api.models.Status
import com.example.wallpapercollect.api.models.UserLogIn
import com.example.wallpapercollect.api.models.UserRegister
import com.example.wallpapercollect.repository.WallpaperCollectRepo
import javax.inject.Inject

class WallpaperCollectRepoImpl @Inject constructor(
    private val wallpaperCollectAPI: WallpaperCollectAPI
): WallpaperCollectRepo {
    override suspend fun userRegister(user: UserRegister): Status {
        return wallpaperCollectAPI.userRegister(user)
    }

    override suspend fun userLogin(user: UserLogIn): Status {
        return wallpaperCollectAPI.userLogin(user)
    }

    override suspend fun userLogout() {
        return wallpaperCollectAPI.userLogout()
    }
}