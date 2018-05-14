package com.undabot.jobfair.events.list.usecases

import com.undabot.jobfair.events.entities.Events
import com.undabot.jobfair.events.repository.EventResult
import javax.inject.Inject

class SortEvents @Inject constructor() {

    private val firstDayIndex = 0
    private val secondDayIndex = 1

    fun from(eventResult: EventResult): Events {
        val sortedListPerTime = eventResult.list.sortedBy { it.startTime }
        val groupedMapPerDays = sortedListPerTime.groupBy { it.startTime.dayOfMonth() }
        return Events(
                groupedMapPerDays.values.elementAtOrNull(firstDayIndex).orEmpty(),
                groupedMapPerDays.values.elementAtOrNull(secondDayIndex).orEmpty()
        )
    }
}