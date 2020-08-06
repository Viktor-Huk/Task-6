package com.example.mvpexample.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.mvpexample.R
import com.example.mvpexample.model.Video

class VideoAdapter(
    private val onVideoSelected: (video: Video) -> Unit
) : ListAdapter<Video, VideoViewHolder>(videoDiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.video_item, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = getItem(position)

        holder.itemView.setOnClickListener {
            onVideoSelected(video)
        }

        holder.bind(video)
    }

    companion object {

        private val videoDiffUtilCallback = object : DiffUtil.ItemCallback<Video>() {

            override fun areItemsTheSame(oldVideo: Video, newVideo: Video): Boolean {
                return oldVideo.title == newVideo.title
            }

            override fun areContentsTheSame(oldVideo: Video, newVideo: Video): Boolean {
                return oldVideo == newVideo
            }
        }
    }
}
