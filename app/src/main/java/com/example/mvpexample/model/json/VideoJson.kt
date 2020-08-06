package com.example.mvpexample.model.json
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoJson(

    @Json(name = "url")
    val url: String
)
