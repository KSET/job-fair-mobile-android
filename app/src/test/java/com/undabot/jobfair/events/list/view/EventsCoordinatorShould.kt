package com.undabot.jobfair.events.list.view

import com.nhaarman.mockito_kotlin.verify
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.events.list.usecases.ShowEvents
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EventsCoordinatorShould {

    private lateinit var coordinator: EventsContract.Coordinator

    @Mock private lateinit var presenter: EventsContract.Presenter
    @Mock private lateinit var view: EventsContract.View
    @Mock private lateinit var showEvents: ShowEvents

    @Before
    fun prepare() {
        coordinator = EventsCoordinator(presenter, showEvents)
        coordinator.bind(view)
    }

    @Test
    fun `show presentations when presentations are requested`() {
        When { coordinator.eventsRequested() }
        Then { verify(showEvents).with(presenter) }
    }
}