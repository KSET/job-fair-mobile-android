package com.undabot.jobfair.news.repository

import com.nhaarman.mockitokotlin2.whenever
import com.undabot.jobfair.Given
import com.undabot.jobfair.TestSchedulerProvider
import com.undabot.jobfair.When
import com.undabot.jobfair.equals
import com.undabot.jobfair.networking.services.ApiService
import com.undabot.jobfair.news.entities.News
import com.undabot.jobfair.news.repository.entities.Channel
import com.undabot.jobfair.news.repository.entities.NewsRss
import com.undabot.jobfair.news.repository.entities.RssFeedPage
import com.undabot.jobfair.news.repository.entities.Thumbnail
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsRepositoryShould {
    private lateinit var newsRepository: NewsRepository

    @Mock
    private lateinit var apiService: ApiService
    private val languageIdentifier = "en"
    private val testTitle = "testTitle"
    private val testDate = "date"
    private val testUrl = "url"
    private val testNews: News = News(testTitle, testDate, testUrl)
    private val fakeRssList = RssFeedPage().apply {
        channel = Channel().apply {
            articleList = listOf(NewsRss().apply {
                title = testTitle
                pubDate = testDate
                description = ""
                thumbnail = Thumbnail().apply { url = testUrl }
            })
        }
    }

    @Test
    fun `fetch the rss feed and map it into data objects`() {
        Given {
            whenever(apiService.rssFeed(languageIdentifier))
                    .thenReturn(Single.just(fakeRssList))
            newsRepository = NewsRepositoryImpl(apiService,
                    TestSchedulerProvider.mainScheduler(),
                    languageIdentifier)
        }
        When {
            newsRepository.fetchFeedItems({
                assert(it.size == 1)
                it[0] equals testNews
            }, {})
        }
    }
}