package com.undabot.jobfair.events.view.mappers

import com.nhaarman.mockito_kotlin.whenever
import com.undabot.jobfair.Given
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.equals
import com.undabot.jobfair.events.entities.Event
import com.undabot.jobfair.events.entities.Events
import com.undabot.jobfair.events.view.EventViewModel
import com.undabot.jobfair.events.view.EventsViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EventsViewModelMapperShould {

    private lateinit var mapper: EventsViewModelMapper

    @Mock private lateinit var eventViewModelMapper: EventViewModelMapper
    @Mock private lateinit var event1: Event
    @Mock private lateinit var event2: Event
    @Mock private lateinit var event3: Event
    @Mock private lateinit var eventViewModel1: EventViewModel
    @Mock private lateinit var eventViewModel2: EventViewModel
    @Mock private lateinit var eventViewModel3: EventViewModel

    private lateinit var events: Events
    private lateinit var actualEventsViewModel: EventsViewModel
    private lateinit var expectedFirstDayEventViewModels: List<EventViewModel>
    private lateinit var expectedSecondDayEventViewModels: List<EventViewModel>
    private lateinit var firstDayPresentations: List<Event>
    private lateinit var secondDayPresentations: List<Event>

    @Before
    fun prepare() {
        mapper = EventsViewModelMapper(eventViewModelMapper)
        `mapper results`()
        expectedFirstDayEventViewModels = arrayListOf(eventViewModel1, eventViewModel2, eventViewModel3)
        expectedSecondDayEventViewModels = arrayListOf(eventViewModel3, eventViewModel1)
        firstDayPresentations = arrayListOf(event1, event2, event3)
        secondDayPresentations = arrayListOf(event3, event1)
        events = Events(firstDayPresentations, secondDayPresentations)
    }

    @Test
    fun `map first day events to first day event view models`() {
        Given { events }
        When { `map is requested`() }
        Then { actualEventsViewModel.firstDay equals expectedFirstDayEventViewModels }
    }

    @Test
    fun `mao second day events to second day event view model`() {
        Given { events }
        When { `map is requested`() }
        Then { actualEventsViewModel.secondDay equals expectedSecondDayEventViewModels }
    }

    private fun `map is requested`() {
        actualEventsViewModel = mapper.map(events)
    }

    private fun `mapper results`() {
        whenever(eventViewModelMapper.map(event1)).thenReturn(eventViewModel1)
        whenever(eventViewModelMapper.map(event2)).thenReturn(eventViewModel2)
        whenever(eventViewModelMapper.map(event3)).thenReturn(eventViewModel3)
    }
}