package com.undabot.jobfair.utils

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import javax.inject.Inject

class DateTimeFormatter @Inject constructor() {

    private val dateTimeFormatter = DateTimeFormat.forPattern("MMM dd, kk:mm")
    private val timeFormatter = DateTimeFormat.forPattern("kk:mm")
    private val dateFormatter = DateTimeFormat.forPattern("dd MMM yyyy")

    fun formatToDate(dateTime: DateTime): String = dateFormatter.print(dateTime)

    fun formatToDateAndTime(dateTime: DateTime): String = dateTimeFormatter.print(dateTime)

    fun formatToFullDateTime(startDateTime: DateTime, endDateTime: DateTime): String =
        "${dateTimeFormatter.print(startDateTime)} - ${timeFormatter.print(endDateTime)}"

    fun formatToTime(dateTime: DateTime): String = timeFormatter.print(dateTime)
}