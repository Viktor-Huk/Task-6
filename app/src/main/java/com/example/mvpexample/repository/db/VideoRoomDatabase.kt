package com.example.mvpexample.repository.db
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvpexample.repository.db.entity.VideoEntity

@Database(entities = [VideoEntity::class], version = 1, exportSchema = false)
abstract class VideoRoomDatabase : RoomDatabase() {

    abstract fun videoDao(): VideoDao
}
