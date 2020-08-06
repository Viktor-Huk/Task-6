package com.example.mvpexample.model.json
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DurationJson(

    @Json(name = "text")
    val duration: String
)
