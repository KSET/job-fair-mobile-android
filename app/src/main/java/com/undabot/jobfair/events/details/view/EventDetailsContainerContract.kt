package com.undabot.jobfair.events.details.view

import com.undabot.jobfair.companies.view.models.CompanyViewModel
import com.undabot.jobfair.core.view.BaseCoordinator
import com.undabot.jobfair.core.view.BasePresenter
import com.undabot.jobfair.events.calendar.CalendarInfo
import com.undabot.jobfair.events.view.EventViewModel

interface EventDetailsContainerContract {

    interface Coordinator : BaseCoordinator<View> {
        fun openedWith(params: EventDetailsParams)
        fun addToCalendarPressedFor(calendarInfo: CalendarInfo)
        fun openCompanyDetailsPressedFor(company: CompanyViewModel)
    }

    interface Presenter : BasePresenter<View> {
        fun show(events: List<EventViewModel>)
        fun showEventAt(index: Int)
        fun openCompanyDetailsFor(company: CompanyViewModel)
        fun addToCalendar(calendarInfo: CalendarInfo)
    }

    interface View {
        fun show(events: List<EventViewModel>)
        fun showEventAt(index: Int)
        fun openCompanyDetailsFor(company: CompanyViewModel)
        fun addToCalendar(calendarInfo: CalendarInfo)
    }
}