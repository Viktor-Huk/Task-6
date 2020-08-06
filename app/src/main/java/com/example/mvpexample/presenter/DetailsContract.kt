package com.example.mvpexample.presenter
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

interface DetailsContract {

    @StateStrategyType(AddToEndStrategy::class)
    interface BaseView : MvpView {

        fun showProgressBar()

        fun hideProgressBar()

        @Skip
        fun showSnackBar()

        @AddToEndSingle
        fun showPreview()

        @AddToEndSingle
        fun hidePreview()

        @AddToEndSingle
        fun showPlayButton()

        @AddToEndSingle
        fun hidePlayButton()

        @AddToEndSingle
        fun showVideo()
    }

    interface BasePresenter {

        fun clickButtonSnackBar()

        fun videoStart()

        fun videoPrepared()
    }
}
