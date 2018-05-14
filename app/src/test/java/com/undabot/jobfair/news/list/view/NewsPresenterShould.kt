package com.undabot.jobfair.news.list.view

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.undabot.jobfair.Given
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.news.entities.News
import com.undabot.jobfair.news.list.view.mappers.NewsViewModelMapper
import com.undabot.jobfair.news.list.view.models.NewsViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsPresenterShould {

    private lateinit var presenter: NewsPresenter

    private val testError = "testError"

    @Mock private lateinit var testNews: News
    @Mock private lateinit var viewModel: NewsViewModel
    @Mock private lateinit var view: NewsContract.View
    @Mock private lateinit var mapper: NewsViewModelMapper

    @Before
    fun prepare() {
        presenter = NewsPresenter(mapper)
        presenter.bind(view)
        whenever(mapper.mapFrom(testNews)).thenReturn(viewModel)
    }

    @Test
    fun `display a list of news items and ready state`() {
        Given { presenter }
        When { presenter.displayFeed(listOf(testNews)) }
        Then {
            verify(view).displayReady()
            verify(view).displayFeed(listOf(viewModel))
        }
    }

    @Test
    fun `display an error state`() {
        Given { presenter }
        When { presenter.displayError(testError) }
        Then {
            verify(view).displayReady()
            verify(view).displayError(testError)
        }
    }
}