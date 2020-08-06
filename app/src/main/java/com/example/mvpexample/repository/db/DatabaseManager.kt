package com.example.mvpexample.repository.db
import androidx.room.Room
import com.example.mvpexample.App

object DatabaseManager {

    private var INSTANCE: VideoRoomDatabase? = null

    fun getDatabase(): VideoRoomDatabase? {

        if (INSTANCE == null) {

            val instance = Room.databaseBuilder(
                App.INSTANCE,
                VideoRoomDatabase::class.java,
                "videos_database"
            ).build()

            INSTANCE = instance
        }

        return INSTANCE
    }
}
