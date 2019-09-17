package com.undabot.jobfair.events.details.view

import com.undabot.jobfair.core.view.BaseCoordinator
import com.undabot.jobfair.core.view.BasePresenter
import com.undabot.jobfair.events.details.usecases.EventDetails
import com.undabot.jobfair.events.details.usecases.RateEvent
import com.undabot.jobfair.events.details.usecases.ShowEventDetails
import com.undabot.jobfair.events.view.EventViewModel

interface EventDetailsContract {

    interface View {
        fun showRatingFailed()
        fun showRatingSuccess()
        fun show(eventDetails: EventDetails)
    }

    interface Coordinator : BaseCoordinator<View> {
        fun eventRatedWith(event: EventViewModel, rating: Int)
        fun showEventRequested(eventViewModel: EventViewModel)
    }

    interface Presenter : BasePresenter<View>,
        RateEvent.RatesEvent,
        ShowEventDetails.ShowsEventDetails
}