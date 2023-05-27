package com.example.wallpapercollect.api

import com.example.wallpapercollect.api.models.Status
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
    suspend fun userGoogleRegister() : Unit

    @GET(ApiEndPoints.REGISTER_FACEBOOK_SESSION)
    suspend fun userFacebookRegister() : Unit

    @POST(ApiEndPoints.LOGIN_EMAIL_DEFAULT)
    suspend fun userLogin(
        @Body user: UserLogIn
    ): Status

    @GET(ApiEndPoints.LOGIN_GOOGLE_SESSION)
    suspend fun userGoogleLogin() : Unit

    @GET(ApiEndPoints.LOGIN_FACEBOOK_SESSION)
    suspend fun userFacebookLogin() : Unit

    @GET(ApiEndPoints.LOGOUT)
    suspend fun userLogout()



    /**WALLPAPER PAGE**/
    @GET(ApiEndPoints.WALLPAPER_COLLECTION)
    suspend fun wallpaperCollection(): Unit

    @Multipart
    @POST(ApiEndPoints.UPLOAD_WALLPAPER)
    suspend fun wallpaperUpload(
        @Part image: MultipartBody.Part
    ) : Unit



    /**PROFILE INFO**/
    @GET(ApiEndPoints.VIEW_PROFILE)
    suspend fun profile():Unit

    @Multipart
    @POST(ApiEndPoints.UPLOAD_PROFILE_PICTURE)
    suspend fun profilePictureUpload(
        @Part image: MultipartBody.Part
    ) : Unit

    /*TODO make update profile*/



    /**IMAGES COLLECTIONS**/
    @GET("images/{id}")
    suspend fun getImage(
        @Path("id") imageId:String
    ) : Unit

    @DELETE("images/{id}/delete")
    suspend fun deleteImage(
        @Path("id") imageId:String
    ) : Unit

    @GET("photo_profile/{id}")
    suspend fun getPhotoProfile(
        @Path("id") imageId:String
    ) : Unit

    /**TODO make End Point that update photo profile and delete photo profile**/

}