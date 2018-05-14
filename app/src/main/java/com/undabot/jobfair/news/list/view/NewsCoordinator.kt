package com.undabot.jobfair.news.list.view

import com.undabot.jobfair.core.view.AbsCoordinator
import com.undabot.jobfair.news.list.usecases.GetFreshNewsFeed
import javax.inject.Inject

class NewsCoordinator @Inject
constructor(
        presenter: NewsContract.Presenter,
        private val getFreshNewsFeed: GetFreshNewsFeed
)
    : NewsContract.Coordinator, AbsCoordinator<NewsContract.View, NewsContract.Presenter>(presenter) {

    override fun fetchFeed() = getFreshNewsFeed.fetch(presenter)
}