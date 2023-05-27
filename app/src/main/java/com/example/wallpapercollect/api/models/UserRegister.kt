package com.example.wallpapercollect.api.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserRegister(
    @Json(name = "user_name")
    val userName:String,
    @Json(name = "password")
    val password:String,
    @Json(name = "email")
    val email:String,
    @Json(name = "phone_number")
    val phoneNumber:String
)
