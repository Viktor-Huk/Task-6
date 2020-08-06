package com.example.mvpexample.repository
import android.util.Log
import com.example.mvpexample.App
import com.example.mvpexample.model.Video
import com.example.mvpexample.model.json.ApiDataJson
import com.example.mvpexample.repository.db.DatabaseManager
import com.example.mvpexample.repository.db.entity.VideoEntity
import com.example.mvpexample.repository.network.NetworkService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class Repository {

    private val videoApi = NetworkService.getVideoApi()
    private val videoDao = DatabaseManager.getDatabase()?.videoDao()

    suspend fun getVideos(source: String): List<Video> {

        var videos: List<Video> = listOf()

        if (videoDao != null) {

            videos = if (videoDao.getAllVideos().isNullOrEmpty()) {

                when (source) {
                    DATA_FROM_LOCAL -> {
                        Log.i("tag", "json")
                        loadVideosFromJsonFile()
                    }
                    DATA_FROM_NETWORK -> {
                        Log.i("tag", "network")
                        loadVideosFromServer()
                    }
                }
                videoDao.getAllVideos().map { it.toVideo() }
            } else {
                videoDao.getAllVideos().map { it.toVideo() }
            }
        }

        return videos
    }

    private suspend fun loadVideosFromServer() {

        val list = videoApi.getVideoApi()
            .channelXml
            ?.itemXml
            ?.map {
                val title = it.title.substringBefore('|').trim()
                val participant = it.title.substringAfter('|').trim()
                val description = it.description
                val duration = it.duration
                val videoUrl = it.groupXml?.videoXml?.get(5)?.url
                val thumbnailUrl = it.groupXml?.thumbnailXml?.url

                Video(title, participant, description, duration, videoUrl, thumbnailUrl)
            }
        videoDao?.addVideos(list?.map { VideoEntity(it) })
    }

    private suspend fun loadVideosFromJsonFile() {
        withContext(Dispatchers.IO) {
            try {
                val bufferedReader = App.INSTANCE.assets.open("data.json").bufferedReader()
                val stringResult = bufferedReader.use { it.readText() }

                val moshi = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
                    .adapter(ApiDataJson::class.java)

                val list = moshi
                    .fromJson(stringResult)
                    ?.channelJson
                    ?.itemJson
                    ?.map {
                        val title = it.title.substringBefore('|').trim()
                        val participant = it.title.substringAfter('|').trim()
                        val description = it.description
                        val duration = it.durationJson.duration
                        val videoUrl = it.groupJson.videoJson[5].url
                        val thumbnailUrl = it.groupJson.thumbnailJson.url

                        Video(title, participant, description, duration, videoUrl, thumbnailUrl)
                    }

                videoDao?.addVideos(list?.map { VideoEntity(it) })
            } catch (exc: IOException) {
                exc.printStackTrace()
            }
        }
    }

    companion object {
        const val DATA_FROM_LOCAL = "local"
        const val DATA_FROM_NETWORK = "network"
    }
}
