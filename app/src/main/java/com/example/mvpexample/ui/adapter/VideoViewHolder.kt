package com.example.mvpexample.ui.adapter
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvpexample.App
import com.example.mvpexample.R
import com.example.mvpexample.model.Video

class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var imageVideo: ImageView = view.findViewById(R.id.image)
    var participant: TextView = view.findViewById(R.id.participant)
    var titleVideo: TextView = view.findViewById(R.id.titleVideo)
    var durationVideo: TextView = view.findViewById(R.id.durationVideo)

    fun bind(video: Video) {

        Glide
            .with(App.INSTANCE)
            .load(video.thumbnailUrl)
            .placeholder(R.drawable.ic_baseline_error_outline_24)
            .into(imageVideo)

        participant.text = (video.participant)
        titleVideo.text = video.title
        durationVideo.text = video.duration
    }
}
