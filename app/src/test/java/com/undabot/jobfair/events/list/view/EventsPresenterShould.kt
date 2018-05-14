package com.undabot.jobfair.events.list.view

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.undabot.jobfair.Given
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.events.entities.Events
import com.undabot.jobfair.events.view.EventsViewModel
import com.undabot.jobfair.events.view.mappers.EventsViewModelMapper
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EventsPresenterShould {

    private lateinit var presenter: EventsContract.Presenter

    @Mock private lateinit var view: EventsContract.View
    @Mock private lateinit var events: Events
    @Mock private lateinit var eventsViewModel: EventsViewModel
    @Mock private lateinit var eventsViewModelMapper: EventsViewModelMapper

    @Before
    fun prepare() {
        presenter = EventsPresenter(eventsViewModelMapper)
        presenter.bind(view)
        whenever(eventsViewModelMapper.map(events)).thenReturn(eventsViewModel)
    }

    @Test
    fun `show general error message when presentations loading fails`() {
        When { presenter.eventsFailedToLoad("") }
        Then { verify(view).showGeneralErrorMessage() }
    }

    @Test
    fun `show loading view when presentations loading is in progress`() {
        When { presenter.eventsLoadingInProgress() }
        Then { verify(view).showLoading() }
    }

    @Test
    fun `show presentations when presentations are loaded`() {
        Given { events }
        When { presenter.eventsLoaded(events) }
        Then { verify(view).showEvents(eventsViewModel) }
    }
}