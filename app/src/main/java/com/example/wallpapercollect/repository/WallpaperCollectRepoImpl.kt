package com.example.wallpapercollect.repository

import com.example.wallpapercollect.api.WallpaperCollectAPI
import com.example.wallpapercollect.api.models.Status
import com.example.wallpapercollect.api.models.UserLogIn
import com.example.wallpapercollect.api.models.UserRegister
import com.example.wallpapercollect.repository.WallpaperCollectRepo
import okhttp3.MultipartBody
import javax.inject.Inject

class WallpaperCollectRepoImpl @Inject constructor(
    private val wallpaperCollectAPI: WallpaperCollectAPI
): WallpaperCollectRepo {
    override suspend fun userRegister(user: UserRegister): Status {
        return wallpaperCollectAPI.userRegister(user)
    }

    override suspend fun userGoogleRegister() {
        return wallpaperCollectAPI.userGoogleRegister()
    }

    override suspend fun userFacebookRegister() {
        return wallpaperCollectAPI.userFacebookRegister()
    }

    override suspend fun userLogin(user: UserLogIn): Status {
        return wallpaperCollectAPI.userLogin(user)
    }

    override suspend fun userGoogleLogin() {
        return wallpaperCollectAPI.userGoogleLogin()
    }

    override suspend fun userFacebookLogin() {
        return wallpaperCollectAPI.userFacebookLogin()
    }

    override suspend fun userLogout() {
        return wallpaperCollectAPI.userLogout()
    }

    override suspend fun wallpaperCollection() {
        return wallpaperCollectAPI.wallpaperCollection()
    }

    override suspend fun wallpaperUpload(image: MultipartBody.Part) {
        return wallpaperCollectAPI.wallpaperUpload(image)
    }

    override suspend fun profile() {
        return wallpaperCollectAPI.profile()
    }

    override suspend fun profilePictureUpload(image: MultipartBody.Part) {
        return wallpaperCollectAPI.profilePictureUpload(image)
    }

    override suspend fun getImages(imageId: String) {
        return wallpaperCollectAPI.getImage(imageId)
    }

    override suspend fun deleteImage(imageId: String) {
        return wallpaperCollectAPI.deleteImage(imageId)
    }

    override suspend fun getPhotoProfile(imageId: String) {
        return wallpaperCollectAPI.getPhotoProfile(imageId)
    }

}