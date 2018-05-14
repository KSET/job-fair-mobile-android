package com.undabot.jobfair.news.list.usecases

import com.undabot.jobfair.news.entities.News
import com.undabot.jobfair.news.repository.NewsRepository
import javax.inject.Inject

class GetFreshNewsFeed @Inject
constructor(private val repository: NewsRepository) {

    fun fetch(presenter: DisplayFeed) {
        repository.fetchFeedItems(
                { presenter.displayFeed(it) },
                { presenter.displayError(it) })
    }

    interface DisplayFeed {
        fun displayLoadingFeed()
        fun displayFeed(items: List<News>)
        fun displayError(message: String)
    }
}
