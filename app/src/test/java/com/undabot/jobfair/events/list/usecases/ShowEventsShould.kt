package com.undabot.jobfair.events.list.usecases

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.undabot.jobfair.Given
import com.undabot.jobfair.TestSchedulerProvider
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.events.entities.Events
import com.undabot.jobfair.events.repository.EventResult
import com.undabot.jobfair.events.repository.EventsRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ShowEventsShould {

    private lateinit var showEvents: ShowEvents

    @Mock private lateinit var repository: EventsRepository
    @Mock private lateinit var presenter: ShowEvents.ShowsEvents
    @Mock private lateinit var sortEvents: SortEvents
    private val eventResult: EventResult = EventResult()
    private val events: Events = Events()

    @Before
    fun prepare() {
        showEvents = ShowEvents(repository, sortEvents, TestSchedulerProvider.mainScheduler())
        whenever(sortEvents.from(eventResult)).thenReturn(events)
    }

    @Test
    fun `notify presenter when error occurs`() {
        Given { `repository can't provide presentations result`() }
        When { `show presentations is requested`() }
        Then { verify(presenter).eventsFailedToLoad("") }
    }

    @Test
    fun `notify presenter when loading starts`() {
        Given { `repository can provide presentations result`() }
        When { `show presentations is requested`() }
        Then { verify(presenter).eventsLoadingInProgress() }
    }

    @Test
    fun `notify presenter when presentations are loaded`() {
        Given { `repository can provide presentations result`() }
        When { `show presentations is requested`() }
        Then { verify(presenter).eventsLoaded(events) }
    }

    private fun `repository can provide presentations result`() {
        whenever(repository.getEvents()).thenReturn(Single.just(eventResult))
    }

    private fun `repository can't provide presentations result`() {
        whenever(repository.getEvents()).thenReturn(Single.error(Throwable()))
    }

    private fun `show presentations is requested`() {
        showEvents.with(presenter)
    }
}