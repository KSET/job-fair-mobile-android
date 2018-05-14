package com.undabot.jobfair.events.view

data class EventsViewModel(
        val firstDay: List<EventViewModel> = arrayListOf(),
        val secondDay: List<EventViewModel> = arrayListOf()
)