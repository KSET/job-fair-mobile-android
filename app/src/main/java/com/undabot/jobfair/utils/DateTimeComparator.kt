package com.undabot.jobfair.utils

import org.joda.time.DateTime
import javax.inject.Inject

class DateTimeComparator @Inject constructor() {

    fun isNow(startDateTime: DateTime, endDateTime: DateTime): Boolean =
            startDateTime.isBeforeNow && endDateTime.isAfterNow

    fun isInPast(startDateTime: DateTime): Boolean = startDateTime.isBeforeNow
}