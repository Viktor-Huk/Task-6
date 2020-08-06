package com.example.mvpexample.model.json
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GroupJson(

    @Json(name = "content")
    val videoJson: List<VideoJson>,

    @Json(name = "thumbnail")
    val thumbnailJson: ThumbnailJson
)
