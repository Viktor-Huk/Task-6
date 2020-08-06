package com.example.mvpexample.repository.network
import com.example.mvpexample.model.xml.ApiDataXml
import retrofit2.http.GET

interface VideoApi {

    @GET("themes/rss/id")
    suspend fun getVideoApi(): ApiDataXml
}
