package com.example.mvpexample.presenter
import com.example.mvpexample.model.Video
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

interface MainContract {

    @StateStrategyType(AddToEndStrategy::class)
    interface BaseView : MvpView {

        @AddToEndSingle
        fun showListOfVideos(videos: List<Video>)

        fun showProgressBar()

        fun hideProgressBar()

        @Skip
        fun showSnackBar()
    }

    interface BasePresenter {

        fun clickButtonSnackBar()
    }
}
