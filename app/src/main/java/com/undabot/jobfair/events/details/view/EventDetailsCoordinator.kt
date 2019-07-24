package com.undabot.jobfair.events.details.view

import com.undabot.jobfair.core.view.AbsCoordinator
import com.undabot.jobfair.events.details.models.RateParams
import com.undabot.jobfair.events.details.usecases.RateEvent
import com.undabot.jobfair.events.details.usecases.ShowEventDetails
import com.undabot.jobfair.events.details.view.EventDetailsContract.Coordinator
import com.undabot.jobfair.events.details.view.EventDetailsContract.Presenter
import com.undabot.jobfair.events.details.view.EventDetailsContract.View
import com.undabot.jobfair.events.view.EventType
import com.undabot.jobfair.events.view.EventViewModel
import javax.inject.Inject

class EventDetailsCoordinator @Inject constructor(
    presenter: Presenter,
    private val rateEvent: RateEvent,
    private val showEventDetails: ShowEventDetails
) : AbsCoordinator<View, Presenter>(presenter), Coordinator {

    override fun showEventRequested(eventViewModel: EventViewModel) {
        showEventDetails(eventViewModel, presenter)
    }

    override fun eventRatedWith(event: EventViewModel, rating: Int) {
        val presentationId = if (event.type == EventType.PRESENTATION) event.id else null
        val workshopId = if (event.type == EventType.WORKSHOP) event.id else null
        rateEvent(RateParams(
            presentationId = presentationId,
            workshopId = workshopId,
            value = rating
        ), presenter)
    }
}