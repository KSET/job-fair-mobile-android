package com.undabot.jobfair.news.list.view

import com.undabot.jobfair.core.view.BaseCoordinator
import com.undabot.jobfair.core.view.BasePresenter
import com.undabot.jobfair.news.list.usecases.GetFreshNewsFeed
import com.undabot.jobfair.news.list.view.models.NewsViewModel

interface NewsContract {

    interface View {
        fun displayFeed(items: List<NewsViewModel>)
        fun displayError(errorMessage: String)
        fun displayLoading()
        fun displayReady()
    }

    interface Coordinator : BaseCoordinator<View> {
        fun fetchFeed()
    }

    interface Presenter : BasePresenter<View>, GetFreshNewsFeed.DisplayFeed

}