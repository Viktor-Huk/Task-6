package com.example.mvpexample.ui
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.MediaController
import coil.ImageLoader
import coil.request.LoadRequest
import com.example.mvpexample.R
import com.example.mvpexample.databinding.ActivityDetailsBinding
import com.example.mvpexample.presenter.DetailsContract
import com.example.mvpexample.presenter.DetailsPresenter
import com.google.android.material.snackbar.Snackbar
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class DetailsActivity : MvpAppCompatActivity(), DetailsContract.BaseView {

    @InjectPresenter
    lateinit var detailsPresenter: DetailsPresenter

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.activityDetailsToolBar)
        supportActionBar?.apply {
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
        binding.activityDetailsToolBar.setNavigationOnClickListener {
            onBackPressed()
        }
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

    override fun showPreview() {
        binding.durationVideo.text = intent.extras?.get(DURATION_VIDEO).toString()

        val loader = ImageLoader(this@DetailsActivity)
        val req = LoadRequest.Builder(this@DetailsActivity)
            .data(intent.extras?.get(THUMBNAIL_URL_VIDEO).toString())
            .target {
                binding.videoView.background = it
            }
            .build()
        loader.execute(req)

        binding.apply {
            titleVideo.text = intent.extras?.get(TITLE_VIDEO).toString()
            participantVideo.text = intent.extras?.get(PARTICIPANT_VIDEO).toString()
            descriptionVideo.text = intent.extras?.get(DESCRIPTION_VIDEO).toString()
            videoView.setVideoPath(intent.extras?.get(URL_VIDEO).toString())
            val mediaController = MediaController(this@DetailsActivity)
            mediaController.setAnchorView(videoView)
            videoView.setMediaController(mediaController)
        }

        binding.durationVideo.visibility = View.VISIBLE

        binding.videoView.setOnPreparedListener {
            detailsPresenter.videoPrepared()
        }
    }

    override fun showVideo() {
        binding.videoView.start()
    }

    override fun hidePreview() {
        binding.videoView.background = null
        binding.durationVideo.visibility = View.INVISIBLE
    }

    override fun showPlayButton() {
        binding.playButton.visibility = View.VISIBLE
        binding.playButton.setOnClickListener {
            detailsPresenter.videoStart()
        }
    }

    override fun hidePlayButton() {
        binding.playButton.visibility = View.INVISIBLE
    }

    override fun showProgressBar() {
        binding.detailsProgressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        binding.detailsProgressBar.visibility = View.INVISIBLE
    }

    override fun showSnackBar() {
        Snackbar.make(
            binding.root, R.string.snack_bar_text, Snackbar.LENGTH_INDEFINITE
        )
            .setAction(R.string.snack_bar_button_text) {
                detailsPresenter.clickButtonSnackBar()
            }
            .setBackgroundTint(resources.getColor(R.color.colorPrimary))
            .show()
    }

    companion object {
        const val TITLE_VIDEO = "title"
        const val PARTICIPANT_VIDEO = "participant"
        const val DESCRIPTION_VIDEO = "description"
        const val DURATION_VIDEO = "duration"
        const val URL_VIDEO = "videoUrl"
        const val THUMBNAIL_URL_VIDEO = "thumbnailUrl"
    }
}
