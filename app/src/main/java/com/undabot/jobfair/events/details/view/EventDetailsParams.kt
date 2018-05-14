package com.undabot.jobfair.events.details.view

import android.annotation.SuppressLint
import android.os.Parcelable
import com.undabot.jobfair.events.entities.Event
import com.undabot.jobfair.events.view.EventViewModel
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class EventDetailsParams(
        val showEventAtIndex: Int = 0,
        val events: List<EventViewModel>,
        val eventType: Event.Type = Event.Type.PRESENTATION
) : Parcelable