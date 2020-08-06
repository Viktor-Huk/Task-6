package com.example.mvpexample.model.json
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ThumbnailJson(

    @Json(name = "url")
    val url: String
)
