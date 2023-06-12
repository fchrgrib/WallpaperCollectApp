package com.example.wallpapercollect.api.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImagesCollections(
    @Json(name = "wallpaper_collection")
    val wallpaperCollection: List<UrlAndId>,
    @Json(name = "status")
    val status : String
)


@JsonClass(generateAdapter = true)
data class UrlAndId(
    @Json(name = "image_url")
    val imageUrls: String,
    @Json(name = "image_name")
    val imageName: String,
    @Json(name = "image_id")
    val imageId:String
)