package com.undabot.jobfair.news.list.view

import com.nhaarman.mockitokotlin2.verify
import com.undabot.jobfair.Given
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.news.list.usecases.GetFreshNewsFeed
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsCoordinatorShould {

    private lateinit var coordinator: NewsContract.Coordinator

    @Mock private lateinit var presenter: NewsContract.Presenter
    @Mock private lateinit var getFeedUseCase: GetFreshNewsFeed

    @Before
    fun prepare() {
        coordinator = NewsCoordinator(presenter, getFeedUseCase)
    }

    @Test
    fun `request feed when asked to fetch`() {
        Given { coordinator }
        When { coordinator.fetchFeed() }
        Then { verify(getFeedUseCase).fetch(presenter) }
    }
}