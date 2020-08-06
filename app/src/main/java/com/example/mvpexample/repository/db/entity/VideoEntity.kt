package com.example.mvpexample.repository.db.entity
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mvpexample.model.Video

@Entity(tableName = "videos_table")
data class VideoEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "participant")
    val participant: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "duration")
    val duration: String,

    @ColumnInfo(name = "videoUrl")
    val videoUrl: String?,

    @ColumnInfo(name = "thumbnailUrl")
    val thumbnailUrl: String?
) {
    constructor(video: Video) : this(
        null,
        title = video.title,
        participant = video.participant,
        description = video.description,
        duration = video.duration,
        videoUrl = video.videoUrl,
        thumbnailUrl = video.thumbnailUrl
    )

    fun toVideo() = Video(title, participant, description, duration, videoUrl, thumbnailUrl)
}
