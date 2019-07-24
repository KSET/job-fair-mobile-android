package com.undabot.jobfair.events.details.view

import com.undabot.jobfair.companies.view.models.CompanyViewModel
import com.undabot.jobfair.core.view.AbsPresenter
import com.undabot.jobfair.events.calendar.CalendarInfo
import com.undabot.jobfair.events.view.EventViewModel
import javax.inject.Inject

class EventDetailsContainerPresenter
@Inject constructor() : AbsPresenter<EventDetailsContainerContract.View>(),
        EventDetailsContainerContract.Presenter {

    override fun show(events: List<EventViewModel>) = onView { it.show(events) }

    override fun showEventAt(index: Int) = onView { it.showEventAt(index) }

    override fun openCompanyDetailsFor(company: CompanyViewModel) =
            onView { it.openCompanyDetailsFor(company) }

    override fun addToCalendar(calendarInfo: CalendarInfo) =
            onView { it.addToCalendar(calendarInfo) }
}