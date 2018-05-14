package com.undabot.jobfair.events.view.mappers

import com.undabot.jobfair.events.calendar.CalendarInfoMapper
import com.undabot.jobfair.events.entities.Event
import com.undabot.jobfair.events.entities.Presentation
import com.undabot.jobfair.events.view.EventViewModel
import com.undabot.jobfair.utils.DateTimeComparator
import com.undabot.jobfair.utils.DateTimeFormatter
import javax.inject.Inject

class EventViewModelMapper @Inject constructor(
        private val dateTimeFormatter: DateTimeFormatter,
        private val dateTimeComparator: DateTimeComparator,
        private val calendarInfoMapper: CalendarInfoMapper
) {

    fun map(event: Event) = EventViewModel(
            id = event.id,
            title = event.title,
            startTime = dateTimeFormatter.formatToDateAndTime(event.startTime),
            endTime = dateTimeFormatter.formatToDateAndTime(event.endTime),
            fullTime = dateTimeFormatter.formatToFullDateTime(event.startTime, event.endTime),
            description = event.description,
            presentersImage = presenterImageFrom(event),
            presentersBio = presenterBioFrom(event),
            currentlyInProgress = dateTimeComparator.isNow(event.startTime, event.endTime),
            inPast = dateTimeComparator.isInPast(event.startTime),
            companyId = event.company.id,
            companyImage = event.company.image.large,
            companyName = event.company.name,
            calendarInfo = calendarInfoFrom(event),
            location = event.location
    )

    private fun presenterBioFrom(event: Event): String =
            (event as? Presentation)?.presentersBio ?: ""

    private fun presenterImageFrom(event: Event): String =
            (event as? Presentation)?.presentersImage?.thumb ?: ""

    private fun calendarInfoFrom(event: Event) = calendarInfoMapper.map(event)
}