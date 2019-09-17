package com.undabot.jobfair.news.list.usecases

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.undabot.jobfair.And
import com.undabot.jobfair.Given
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.news.entities.News
import com.undabot.jobfair.news.repository.NewsRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetFreshNewsFeedShould {

    private lateinit var freshNewsFeed: GetFreshNewsFeed

    @Mock private lateinit var repository: NewsRepository
    @Mock private lateinit var presenter: GetFreshNewsFeed.DisplayFeed

    private val testError = "Error"
    private val testList = listOf(News("title", "time", "time", "url"))

    @Before
    fun prepare() {
        freshNewsFeed = GetFreshNewsFeed(repository)
    }

    @Test
    fun `request and pass models to presenter when successful`() {
        Given { freshNewsFeed } And { `repository returns a successful response`() }
        When { freshNewsFeed.fetch(presenter) }
        Then { verify(presenter).displayFeed(testList) }
    }

    @Test
    fun `request feed and pass error to presenter when it fails`() {
        Given { freshNewsFeed } And { `repository returns an error`() }
        When { freshNewsFeed.fetch(presenter) }
        Then { verify(presenter).displayError(testError) }
    }

    private fun `repository returns a successful response`() {
        whenever(repository.fetchFeedItems(any(), any())).then {
            (it.arguments[0] as (List<News>) -> Unit).invoke(testList)
        }
    }

    private fun `repository returns an error`() {
        whenever(repository.fetchFeedItems(any(), any()))
                .then { (it.arguments[1] as (String) -> Unit).invoke(testError) }
    }
}