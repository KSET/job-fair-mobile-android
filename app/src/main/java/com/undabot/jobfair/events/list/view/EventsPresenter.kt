package com.undabot.jobfair.events.list.view

import com.undabot.jobfair.core.view.AbsPresenter
import com.undabot.jobfair.events.entities.Events
import com.undabot.jobfair.events.view.mappers.EventsViewModelMapper
import javax.inject.Inject

class EventsPresenter @Inject constructor(
        private val mapper: EventsViewModelMapper
) : AbsPresenter<EventsContract.View>(),
        EventsContract.Presenter {

    override fun eventsLoadingInProgress() = onView { it.showLoading() }

    override fun eventsFailedToLoad(message: String) = onView { it.showGeneralErrorMessage() }

    override fun eventsLoaded(events: Events) = onView { it.showEvents(mapper.map(events)) }
}