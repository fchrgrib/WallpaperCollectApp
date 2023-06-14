package com.example.wallpapercollect.api.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserUpdate(
    @Json(name = "user_name")
    val userName:String,
    @Json(name = "email")
    val email:String,
    @Json(name = "phone_number")
    val phoneNumber:String
)
