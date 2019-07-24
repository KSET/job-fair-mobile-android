package com.undabot.jobfair.events.view

import android.annotation.SuppressLint
import android.os.Parcelable
import com.undabot.jobfair.booths.entities.LocationInfo
import com.undabot.jobfair.events.calendar.CalendarInfo
import kotlinx.android.parcel.Parcelize
import org.joda.time.DateTime

@SuppressLint("ParcelCreator")
@Parcelize
data class EventViewModel(
    val id: String = "",
    val type: EventType,
    val title: String = "",
    val startTime: DateTime = DateTime(),
    val endTime: DateTime = DateTime(),
    val startTimeFormatted: String = "",
    val endTimeFormatted: String = "",
    val fullTime: String = "",
    val description: String = "",
    val presentersImage: String = "",
    val presentersBio: String = "",
    val currentlyInProgress: Boolean = false,
    val inPast: Boolean = false,
    val companyId: String = "",
    val companyImage: String = "",
    val companyName: String = "",
    val calendarInfo: CalendarInfo = CalendarInfo(),
    val location: LocationInfo = LocationInfo()
) : Parcelable

enum class EventType {
    PRESENTATION, WORKSHOP
}