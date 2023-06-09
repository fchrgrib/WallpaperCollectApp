package com.example.wallpapercollect.api

import com.example.wallpapercollect.api.models.ImagesCollections
import com.example.wallpapercollect.api.models.Status
import com.example.wallpapercollect.api.models.Token
import com.example.wallpapercollect.api.models.Url
import com.example.wallpapercollect.api.models.UserDescription
import com.example.wallpapercollect.api.models.UserLogIn
import com.example.wallpapercollect.api.models.UserRegister
import com.example.wallpapercollect.api.models.UserUpdate
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface WallpaperCollectAPI {

    /**AUTH REQUEST**/
    @POST(ApiEndPoints.REGISTER_EMAIL_DEFAULT)
    suspend fun userRegister(
        @Body user: UserRegister
    ): Status

    @POST(ApiEndPoints.REGISTER_GOOGLE_SESSION)
    suspend fun userGoogleRegister(
        @Body token: Token
    ) : Status

    @GET(ApiEndPoints.REGISTER_FACEBOOK_SESSION)
    suspend fun userFacebookRegister() : Url

    @POST(ApiEndPoints.LOGIN_EMAIL_DEFAULT)
    suspend fun userLogin(
        @Body user: UserLogIn
    ): Status

    @POST(ApiEndPoints.LOGIN_GOOGLE_SESSION)
    suspend fun userGoogleLogin(
        @Body token: Token
    ) : Status

    @GET(ApiEndPoints.LOGIN_FACEBOOK_SESSION)
    suspend fun userFacebookLogin() : Url

    @GET(ApiEndPoints.LOGOUT)
    suspend fun userLogout()



    /**WALLPAPER PAGE**/
    @GET(ApiEndPoints.WALLPAPER_COLLECTION)
    suspend fun wallpaperCollection(): ImagesCollections

    @Multipart
    @POST(ApiEndPoints.UPLOAD_WALLPAPER)
    suspend fun wallpaperUpload(
        @Part image: MultipartBody.Part
    ) : Status



    /**PROFILE INFO**/
    @GET(ApiEndPoints.VIEW_PROFILE)
    suspend fun profile():UserDescription

    @DELETE(ApiEndPoints.DELETE_USER)
    suspend fun deleteUser():Status

    @PUT(ApiEndPoints.UPDATE_PROFILE_DESC)
    suspend fun updateProfileDesc(
        @Body userUpdate: UserUpdate
    ):Status

    @Multipart
    @POST(ApiEndPoints.UPLOAD_PROFILE_PICTURE)
    suspend fun profilePictureUpload(
        @Part image: MultipartBody.Part
    ) : Status

    @Multipart
    @PUT(ApiEndPoints.UPDATE_PROFILE_PICTURE)
    suspend fun profilePictureUpdate(
        @Part image: MultipartBody.Part
    ) : Status



    /**IMAGES COLLECTIONS**/
    @GET("images/{id}")
    suspend fun getImage(
        @Path("id") imageId:String
    ) : Any

    @DELETE("images/{id}/delete")
    suspend fun deleteImage(
        @Path("id") imageId:String
    ) : Status

    @GET("photo_profile/{id}")
    suspend fun getPhotoProfile(
        @Path("id") imageId:String
    ) : Status

    /**TODO make End Point that update photo profile and delete photo profile**/

}