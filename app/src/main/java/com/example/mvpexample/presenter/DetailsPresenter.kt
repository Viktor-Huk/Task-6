package com.example.mvpexample.presenter
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.example.mvpexample.App
import com.example.mvpexample.ui.SettingsFragment
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class DetailsPresenter : MvpPresenter<DetailsContract.BaseView>(), DetailsContract.BasePresenter {

    override fun attachView(view: DetailsContract.BaseView?) {
        super.attachView(view)

        val prefs = PreferenceManager.getDefaultSharedPreferences(App.INSTANCE)

        if (prefs.getBoolean(SettingsFragment.KEY_CHANGE_THEME, false)) {
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
            viewState.apply {
                showPreview()
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

    override fun videoStart() {
        viewState.hidePlayButton()
        viewState.hidePreview()
        viewState.showVideo()
    }

    override fun videoPrepared() {
        viewState.hideProgressBar()
        viewState.showPlayButton()
    }

    override fun clickButtonSnackBar() {
        viewState.showProgressBar()

        if (!isNetworkAvailable()) {
            viewState.apply {
                hideProgressBar()
                showSnackBar()
            }
        } else {
            viewState.apply {
                showPreview()
            }
        }
    }
}
