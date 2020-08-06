package com.example.mvpexample.repository.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

object NetworkService {

    private const val BASE_URL_XML = "https://www.ted.com/"

    private val client = OkHttpClient
        .Builder()
        .build()

    private val videoApi = Retrofit.Builder()
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .baseUrl(BASE_URL_XML)
        .client(client)
        .build()
        .create(VideoApi::class.java)

    fun getVideoApi(): VideoApi = videoApi
}
