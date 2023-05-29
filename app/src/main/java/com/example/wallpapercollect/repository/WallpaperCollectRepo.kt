package com.example.wallpapercollect.repository

import android.widget.TextView.SavedState
import com.example.wallpapercollect.api.models.Status
import com.example.wallpapercollect.api.models.Url
import com.example.wallpapercollect.api.models.UserDescription
import com.example.wallpapercollect.api.models.UserLogIn
import com.example.wallpapercollect.api.models.UserRegister
import okhttp3.MultipartBody

interface WallpaperCollectRepo {
    suspend fun userRegister(user: UserRegister): Status
    suspend fun userGoogleRegister() :Url
    suspend fun userFacebookRegister() :Any
    suspend fun userLogin(user: UserLogIn): Status
    suspend fun userGoogleLogin():Any
    suspend fun userFacebookLogin():Any
    suspend fun userLogout()


    suspend fun wallpaperCollection():Any
    suspend fun wallpaperUpload(image : MultipartBody.Part): Status


    suspend fun profile():UserDescription
    suspend fun profilePictureUpload(image : MultipartBody.Part): Any



    suspend fun getImages(imageId :String) : Any
    suspend fun deleteImage(imageId: String):Any
    suspend fun getPhotoProfile(imageId :String) : Any
}