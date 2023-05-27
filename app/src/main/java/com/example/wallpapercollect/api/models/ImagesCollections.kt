package com.example.wallpapercollect.api.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImagesCollections(
    @Json(name = "wallpaper_collection")
    val imageUrl: ArrayList<String>,
    )
