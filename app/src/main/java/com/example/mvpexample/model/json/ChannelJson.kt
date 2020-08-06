package com.example.mvpexample.model.json
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChannelJson(

    @Json(name = "title")
    val title: String,

    @Json(name = "link")
    val link: String,

    @Json(name = "item")
    val itemJson: List<ItemJson>
)
