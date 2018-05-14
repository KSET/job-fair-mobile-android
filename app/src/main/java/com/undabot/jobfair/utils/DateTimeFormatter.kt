package com.undabot.jobfair.utils

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import javax.inject.Inject

class DateTimeFormatter @Inject constructor() {

    private val dateTimeFormatter = DateTimeFormat.forPattern("MMM dd, kk:mm")
    private val timeFormatter = DateTimeFormat.forPattern("kk:mm")

    fun formatToDateAndTime(dateTime: DateTime): String = dateTimeFormatter.print(dateTime)

    fun formatToFullDateTime(startDateTime: DateTime, endDateTime: DateTime): String =
            "${dateTimeFormatter.print(startDateTime)} - ${timeFormatter.print(endDateTime)}"
}