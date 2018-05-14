package com.undabot.jobfair.events.view.mappers

import com.undabot.jobfair.events.entities.Events
import com.undabot.jobfair.events.view.EventsViewModel
import javax.inject.Inject

class EventsViewModelMapper @Inject constructor(
    private val eventViewModelMapper: EventViewModelMapper
) {

    fun map(events: Events) = EventsViewModel(
            firstDay = events.firstDay.map { eventViewModelMapper.map(it) },
            secondDay = events.secondDay.map { eventViewModelMapper.map(it) }
    )
}