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
class EventDetailsPresenterShould {

    private lateinit var presenter: EventDetailsContract.Presenter

    @Mock private lateinit var view: EventDetailsContract.View
    @Mock private lateinit var calendarInfo: CalendarInfo
    private val index = 1
    @Mock private lateinit var events: List<EventViewModel>
    @Mock private lateinit var company: CompanyViewModel

    @Before
    fun prepare() {
        presenter = EventDetailsPresenter()
        presenter.bind(view)
    }

    @Test
    fun `show provided presentations when requested`() {
        When { presenter.show(events) }
        Then { verify(view).show(events) }
    }

    @Test
    fun `show presentation at specific index when requested`() {
        When { presenter.showEventAt(index) }
        Then { verify(view).showEventAt(index) }
    }

    @Test
    fun `open company details for provided id when requested`() {
        When { presenter.openCompanyDetailsFor(company) }
        Then { verify(view).openCompanyDetailsFor(company) }
    }

    @Test
    fun `add to calendar provided calendar info when requested`() {
        When { presenter.addToCalendar(calendarInfo) }
        Then { verify(view).addToCalendar(calendarInfo) }
    }
}