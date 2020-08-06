package com.example.mvpexample
import android.app.Application

class App : Application() {

    init {
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: App
    }
}
