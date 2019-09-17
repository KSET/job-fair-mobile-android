package com.undabot.jobfair.events.calendar

import com.undabot.jobfair.events.entities.Event
import javax.inject.Inject

class CalendarInfoMapper @Inject constructor() {

    fun map(event: Event) = CalendarInfo(
            title = titleFrom(event),
            startTime = event.startTime,
            endTime = event.endTime,
            locationName = event.location.location
    )

    private fun titleFrom(event: Event) = "${event.company.name} - ${event.title}"
}