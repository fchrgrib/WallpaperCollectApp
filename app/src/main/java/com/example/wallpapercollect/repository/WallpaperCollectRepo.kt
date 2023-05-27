package com.example.wallpapercollect.repository

import com.example.wallpapercollect.api.models.Status
import com.example.wallpapercollect.api.models.UserLogIn
import com.example.wallpapercollect.api.models.UserRegister
import okhttp3.MultipartBody

interface WallpaperCollectRepo {
    suspend fun userRegister(user: UserRegister): Status
    suspend fun userGoogleRegister() :Unit
    suspend fun userFacebookRegister() :Unit
    suspend fun userLogin(user: UserLogIn): Status
    suspend fun userGoogleLogin():Unit
    suspend fun userFacebookLogin():Unit
    suspend fun userLogout()


    suspend fun wallpaperCollection():Unit
    suspend fun wallpaperUpload(image : MultipartBody.Part): Unit


    suspend fun profile():Unit
    suspend fun profilePictureUpload(image : MultipartBody.Part)



    suspend fun getImages(imageId :String) : Unit
    suspend fun deleteImage(imageId: String):Unit
    suspend fun getPhotoProfile(imageId :String) : Unit
}