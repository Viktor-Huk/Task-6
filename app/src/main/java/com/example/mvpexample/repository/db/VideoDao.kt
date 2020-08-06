package com.example.mvpexample.repository.db
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvpexample.repository.db.entity.VideoEntity

@Dao
interface VideoDao {

    @Query("SELECT * FROM videos_table")
    suspend fun getAllVideos(): List<VideoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addVideos(videos: List<VideoEntity>?)
}
