package com.undabot.jobfair.events.view

import android.annotation.SuppressLint
import android.os.Parcelable
import com.undabot.jobfair.events.calendar.CalendarInfo
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class EventViewModel(
        val id: String = "",
        val title: String = "",
        val startTime: String = "",
        val endTime: String = "",
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
        val location: String = ""
) : Parcelable