package com.undabot.jobfair.events.list.view

import com.undabot.jobfair.core.view.BaseCoordinator
import com.undabot.jobfair.core.view.BasePresenter
import com.undabot.jobfair.events.list.usecases.ShowEvents
import com.undabot.jobfair.events.view.EventsViewModel

interface EventsContract {

    interface Coordinator : BaseCoordinator<View> {
        fun eventsRequested()
    }

    interface Presenter : BasePresenter<View>,
            ShowEvents.ShowsEvents

    interface View {
        fun showGeneralErrorMessage()
        fun showLoading()
        fun showEvents(eventsViewModel: EventsViewModel)
    }
}