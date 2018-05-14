package com.undabot.jobfair.events.list.view

import com.undabot.jobfair.core.view.AbsCoordinator
import com.undabot.jobfair.events.list.usecases.ShowEvents
import com.undabot.jobfair.events.list.view.EventsContract.Coordinator
import com.undabot.jobfair.events.list.view.EventsContract.Presenter
import com.undabot.jobfair.events.list.view.EventsContract.View
import javax.inject.Inject

class EventsCoordinator @Inject constructor(
    presenter: Presenter,
    private val showEvents: ShowEvents
) : AbsCoordinator<View, Presenter>(presenter), Coordinator {

    override fun eventsRequested() = showEvents.with(presenter)
}