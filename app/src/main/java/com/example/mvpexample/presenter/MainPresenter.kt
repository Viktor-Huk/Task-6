package com.example.mvpexample.presenter

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.example.mvpexample.App
import com.example.mvpexample.repository.Repository
import com.example.mvpexample.ui.SettingsFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.MvpPresenter
import kotlin.coroutines.CoroutineContext

@InjectViewState
class MainPresenter : MainContract.BasePresenter, MvpPresenter<MainContract.BaseView>(),
    CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    private val repository = Repository()
    private val preferences = PreferenceManager.getDefaultSharedPreferences(App.INSTANCE)

    override fun clickButtonSnackBar() {
        viewState.showProgressBar()

        if (!isNetworkAvailable()) {
            viewState.apply {
                hideProgressBar()
                showSnackBar()
            }
        } else {
            launch {

                val videos =
                    if (preferences.getBoolean(SettingsFragment.KEY_CHANGE_SOURCE, false)) {
                        repository.getVideos(Repository.DATA_FROM_LOCAL)
                    } else {
                        repository.getVideos(Repository.DATA_FROM_NETWORK)
                    }

                withContext(Dispatchers.Main) {

                    viewState?.apply {
                        hideProgressBar()
                        showListOfVideos(videos)
                    }
                }
            }
        }
    }

    override fun attachView(view: MainContract.BaseView?) {
        super.attachView(view)
        Log.i("tag", "presenter attach")

        if (preferences.getBoolean(SettingsFragment.KEY_CHANGE_THEME, false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        viewState.showProgressBar()

        if (!isNetworkAvailable()) {
            viewState.apply {
                hideProgressBar()
                showSnackBar()
            }
        } else {
            launch {

                val videos =
                    if (preferences.getBoolean(SettingsFragment.KEY_CHANGE_SOURCE, false)) {
                        repository.getVideos(Repository.DATA_FROM_LOCAL)
                    } else {
                        repository.getVideos(Repository.DATA_FROM_NETWORK)
                    }

                withContext(Dispatchers.Main) {
                    viewState?.apply {
                        hideProgressBar()
                        showListOfVideos(videos)
                    }
                }
            }
        }
    }

    private fun isNetworkAvailable(): Boolean {

        var result = false

        val connectivityManager =
            App.INSTANCE.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

        if (capabilities != null) {

            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    result = true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    result = true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    result = true
                }
            }
        }
        return result
    }
}
