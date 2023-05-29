package com.example.wallpapercollect.repository

import com.example.wallpapercollect.api.WallpaperCollectAPI
import com.example.wallpapercollect.api.models.Status
import com.example.wallpapercollect.api.models.Url
import com.example.wallpapercollect.api.models.UserDescription
import com.example.wallpapercollect.api.models.UserLogIn
import com.example.wallpapercollect.api.models.UserRegister
import okhttp3.MultipartBody
import javax.inject.Inject

class WallpaperCollectRepoImpl @Inject constructor(
    private val wallpaperCollectAPI: WallpaperCollectAPI
): WallpaperCollectRepo {
    override suspend fun userRegister(user: UserRegister): Status {
        return wallpaperCollectAPI.userRegister(user)
    }

    override suspend fun userGoogleRegister(): Url {
        return wallpaperCollectAPI.userGoogleRegister()
    }

    override suspend fun userFacebookRegister(): Url {
        return wallpaperCollectAPI.userFacebookRegister()
    }

    override suspend fun userLogin(user: UserLogIn): Status {
        return wallpaperCollectAPI.userLogin(user)
    }

    override suspend fun userGoogleLogin(): Url {
        return wallpaperCollectAPI.userGoogleLogin()
    }

    override suspend fun userFacebookLogin(): Url {
        return wallpaperCollectAPI.userFacebookLogin()
    }

    override suspend fun userLogout() {
        return wallpaperCollectAPI.userLogout()
    }

    override suspend fun wallpaperCollection(): Any {
        return wallpaperCollectAPI.wallpaperCollection()
    }

    override suspend fun wallpaperUpload(image: MultipartBody.Part): Status {
        return wallpaperCollectAPI.wallpaperUpload(image)
    }

    override suspend fun profile(): UserDescription {
        return wallpaperCollectAPI.profile()
    }

    override suspend fun profilePictureUpload(image: MultipartBody.Part): Any {
        return wallpaperCollectAPI.profilePictureUpload(image)
    }

    override suspend fun getImages(imageId: String): Any {
        return wallpaperCollectAPI.getImage(imageId)
    }

    override suspend fun deleteImage(imageId: String): Any {
        return wallpaperCollectAPI.deleteImage(imageId)
    }

    override suspend fun getPhotoProfile(imageId: String): Any {
        return wallpaperCollectAPI.getPhotoProfile(imageId)
    }

}