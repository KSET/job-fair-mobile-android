package com.undabot.jobfair.events.details.view

import com.undabot.jobfair.companies.view.models.CompanyViewModel
import com.undabot.jobfair.core.view.AbsCoordinator
import com.undabot.jobfair.events.calendar.CalendarInfo
import com.undabot.jobfair.events.details.view.EventDetailsContainerContract.Coordinator
import com.undabot.jobfair.events.details.view.EventDetailsContainerContract.Presenter
import com.undabot.jobfair.events.details.view.EventDetailsContainerContract.View
import javax.inject.Inject

class EventDetailsContainerCoordinator @Inject constructor(
    presenter: Presenter
) : AbsCoordinator<View, Presenter>(presenter), Coordinator {

    override fun openedWith(params: EventDetailsParams) {
        presenter.show(params.events)
        presenter.showEventAt(params.showEventAtIndex)
    }

    override fun addToCalendarPressedFor(calendarInfo: CalendarInfo) {
        presenter.addToCalendar(calendarInfo)
    }

    override fun openCompanyDetailsPressedFor(company: CompanyViewModel) {
        presenter.openCompanyDetailsFor(company)
    }
}