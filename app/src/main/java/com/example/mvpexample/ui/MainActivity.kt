package com.example.mvpexample.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvpexample.R
import com.example.mvpexample.databinding.ActivityMainBinding
import com.example.mvpexample.model.Video
import com.example.mvpexample.presenter.MainContract
import com.example.mvpexample.presenter.MainPresenter
import com.example.mvpexample.ui.adapter.VideoAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import kotlin.coroutines.CoroutineContext

class MainActivity : MvpAppCompatActivity(), MainContract.BaseView, CoroutineScope {

    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.Main

    private lateinit var binding: ActivityMainBinding
    private val videoAdapter = VideoAdapter((::openVideo)())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mainToolBar)

        binding.apply {
            videoRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            videoRecyclerView.adapter = videoAdapter
            videoRecyclerView.addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    LinearLayoutManager.VERTICAL
                )
            )
        }

        binding.swipeRefreshContainer.setOnRefreshListener {
            // TODO load new items

            binding.swipeRefreshContainer.postDelayed({
                binding.swipeRefreshContainer.isRefreshing = false
            }, 2000L)
        }

        Log.i("tag", "main create")
    }

    override fun showListOfVideos(videos: List<Video>) {
        videoAdapter.submitList(videos)
        Log.i("tag", "main show list")
    }

    override fun showProgressBar() {
        binding.mainProgressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        binding.mainProgressBar.visibility = View.INVISIBLE
    }

    override fun showSnackBar() {
        Snackbar.make(
            binding.root, R.string.snack_bar_text, Snackbar.LENGTH_INDEFINITE
        )
            .setAction(R.string.snack_bar_button_text) {
                mainPresenter.clickButtonSnackBar()
            }
            .setBackgroundTint(resources.getColor(R.color.colorPrimary))
            .show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.settings_item_menu -> {
                val settingsIntent = Intent(this, SettingsActivity::class.java)
                startActivity(settingsIntent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openVideo(): (Video) -> Unit = {
        val intentDetails = Intent(this, DetailsActivity::class.java)
        intentDetails.apply {
            putExtra(DetailsActivity.TITLE_VIDEO, it.title)
            putExtra(DetailsActivity.PARTICIPANT_VIDEO, it.participant)
            putExtra(DetailsActivity.DESCRIPTION_VIDEO, it.description)
            putExtra(DetailsActivity.DURATION_VIDEO, it.duration)
            putExtra(DetailsActivity.URL_VIDEO, it.videoUrl)
            putExtra(DetailsActivity.THUMBNAIL_URL_VIDEO, it.thumbnailUrl)
        }
        startActivity(intentDetails)
        Log.i("tag", "open video")
    }
}
