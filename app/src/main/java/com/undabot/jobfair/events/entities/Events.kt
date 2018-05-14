package com.undabot.jobfair.events.entities

data class Events(
        val firstDay: List<Event> = arrayListOf(),
        val secondDay: List<Event> = arrayListOf()
)