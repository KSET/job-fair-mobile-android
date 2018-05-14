package com.undabot.jobfair.events.calendar

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.joda.time.DateTime

@SuppressLint("ParcelCreator")
@Parcelize
data class CalendarInfo(
    val title: String = "",
    val startTime: DateTime = DateTime(),
    val endTime: DateTime = DateTime(),
    val locationName: String = ""
) : Parcelable