package com.undabot.jobfair.events.details.view

import com.nhaarman.mockito_kotlin.verify
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.companies.view.models.CompanyViewModel
import com.undabot.jobfair.events.calendar.CalendarInfo
import com.undabot.jobfair.events.view.EventViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EventDetailsCoordinatorShould {

    private lateinit var coordinator: EventDetailsContract.Coordinator

    @Mock private lateinit var presenter: EventDetailsContract.Presenter
    @Mock private lateinit var view: EventDetailsContract.View
    @Mock private lateinit var calendarInfo: CalendarInfo
    @Mock private lateinit var company: CompanyViewModel
    private val index = 1
    private val events: List<EventViewModel> = arrayListOf(EventViewModel("1"))
    private val params: EventDetailsParams = EventDetailsParams(index, events)

    @Before
    fun prepare() {
        coordinator = EventDetailsCoordinator(presenter)
        coordinator.bind(view)
    }

    @Test
    fun `show presentations when view is shown`() {
        When { coordinator.openedWith(params) }
        Then { verify(presenter).show(events) }
    }

    @Test
    fun `show specific presentation when view is shown`() {
        When { coordinator.openedWith(params) }
        Then { verify(presenter).showEventAt(index) }
    }

    @Test
    fun `open company details when requested`() {
        When { coordinator.openCompanyDetailsPressedFor(company) }
        Then { verify(presenter).openCompanyDetailsFor(company) }
    }

    @Test
    fun `add to calendar requested calendar info`() {
        When { coordinator.addToCalendarPressedFor(calendarInfo) }
        Then { verify(presenter).addToCalendar(calendarInfo) }
    }
}