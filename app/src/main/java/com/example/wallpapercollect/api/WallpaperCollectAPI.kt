package com.example.wallpapercollect.api

import com.example.wallpapercollect.api.models.Status
import com.example.wallpapercollect.api.models.Url
import com.example.wallpapercollect.api.models.UserDescription
import com.example.wallpapercollect.api.models.UserRegister
import com.example.wallpapercollect.api.models.UserLogIn
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface WallpaperCollectAPI {

    /**AUTH REQUEST**/
    @POST(ApiEndPoints.REGISTER_EMAIL_DEFAULT)
    suspend fun userRegister(
        @Body user: UserRegister
    ): Status

    @GET(ApiEndPoints.REGISTER_GOOGLE_SESSION)
    suspend fun userGoogleRegister() : Url

    @GET(ApiEndPoints.REGISTER_FACEBOOK_SESSION)
    suspend fun userFacebookRegister() : Any

    @POST(ApiEndPoints.LOGIN_EMAIL_DEFAULT)
    suspend fun userLogin(
        @Body user: UserLogIn
    ): Status

    @GET(ApiEndPoints.LOGIN_GOOGLE_SESSION)
    suspend fun userGoogleLogin() : Any

    @GET(ApiEndPoints.LOGIN_FACEBOOK_SESSION)
    suspend fun userFacebookLogin() : Any

    @GET(ApiEndPoints.LOGOUT)
    suspend fun userLogout()



    /**WALLPAPER PAGE**/
    @GET(ApiEndPoints.WALLPAPER_COLLECTION)
    suspend fun wallpaperCollection(): Any

    @Multipart
    @POST(ApiEndPoints.UPLOAD_WALLPAPER)
    suspend fun wallpaperUpload(
        @Part image: MultipartBody.Part
    ) : Status



    /**PROFILE INFO**/
    @GET(ApiEndPoints.VIEW_PROFILE)
    suspend fun profile():UserDescription

    @Multipart
    @POST(ApiEndPoints.UPLOAD_PROFILE_PICTURE)
    suspend fun profilePictureUpload(
        @Part image: MultipartBody.Part
    ) : Any

    /*TODO make update profile*/



    /**IMAGES COLLECTIONS**/
    @GET("images/{id}")
    suspend fun getImage(
        @Path("id") imageId:String
    ) : Any

    @DELETE("images/{id}/delete")
    suspend fun deleteImage(
        @Path("id") imageId:String
    ) : Any

    @GET("photo_profile/{id}")
    suspend fun getPhotoProfile(
        @Path("id") imageId:String
    ) : Any

    /**TODO make End Point that update photo profile and delete photo profile**/

}