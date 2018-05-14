package com.undabot.jobfair.about.view

import com.undabot.jobfair.core.view.BaseCoordinator
import com.undabot.jobfair.core.view.BasePresenter

interface AboutContract {

    interface Coordinator : BaseCoordinator<View> {
        fun onMapPressed()
        fun onWebPressed()
        fun onEmailPressed()
        fun onFacebookPressed()
        fun onInstagramPressed()
        fun onYouTubeChannelPressed()
        fun onDayOneStreamPressed()
        fun onDayTwoStreamPressed()
        fun onSharePressed()
        fun onDeveloperInfoPressed()
    }

    interface Presenter : BasePresenter<View> {
        fun openUrl(url: String)
        fun shareUrl(url: String)
    }

    interface View {
        fun openUrl(url: String)
        fun shareUrl(url: String)
    }
}