package com.undabot.jobfair.events.list.usecases

import com.undabot.jobfair.core.schedulers.MainScheduler
import com.undabot.jobfair.events.entities.Events
import com.undabot.jobfair.events.repository.EventsRepository
import javax.inject.Inject

class ShowEvents @Inject constructor(
        private val repository: EventsRepository,
        private val sortEvents: SortEvents,
        private val mainScheduler: MainScheduler
) {

    fun with(presenter: ShowsEvents) {
        repository.getEvents()
                .observeOn(mainScheduler.get())
                .doOnSubscribe { presenter.eventsLoadingInProgress() }
                .subscribe(
                        { presenter.eventsLoaded(sortEvents.from(it)) },
                        { presenter.eventsFailedToLoad(it.message.orEmpty()) })
    }

    interface ShowsEvents {
        fun eventsLoadingInProgress()
        fun eventsFailedToLoad(message: String)
        fun eventsLoaded(events: Events)
    }
}