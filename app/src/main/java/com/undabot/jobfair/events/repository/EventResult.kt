package com.undabot.jobfair.events.repository

import com.undabot.jobfair.events.entities.Event

data class EventResult(val list: List<Event> = arrayListOf())