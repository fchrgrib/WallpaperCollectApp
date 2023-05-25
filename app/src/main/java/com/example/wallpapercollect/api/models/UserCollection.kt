package com.example.wallpapercollect.api.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserCollection(
    @Json(name = "user_name")
    val userName:String,
    @Json(name = "email")
    val email:String,
    @Json(name = "phone_number")
    val phoneNumber:Int,
    @Json(name = "description")
    val description:String
)
