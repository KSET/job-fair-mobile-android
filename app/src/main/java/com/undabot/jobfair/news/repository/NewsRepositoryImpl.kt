package com.undabot.jobfair.news.repository

import com.undabot.jobfair.core.schedulers.MainScheduler
import com.undabot.jobfair.networking.services.ApiService
import com.undabot.jobfair.news.entities.News
import javax.inject.Inject
import javax.inject.Named

class NewsRepositoryImpl @Inject constructor(
        private val apiService: ApiService,
        private val scheduler: MainScheduler,
        @Named("language")
        private val languageIdentifier: String
) : NewsRepository {

    override fun fetchFeedItems(success: (List<News>) -> Unit, error: (String) -> Unit) {
        apiService.rssFeed(languageIdentifier)
                .observeOn(scheduler.get())
                .subscribe({
                    success(it.channel.articleList
                            .map { News(it.title, it.pubDate, it.thumbnail.url, it.description) })
                }, {
                    error("A network error occurred.")
                })
    }
}