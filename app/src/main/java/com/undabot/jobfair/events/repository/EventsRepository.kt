package com.undabot.jobfair.events.repository

import io.reactivex.Single

interface EventsRepository {

    fun getEvents(): Single<EventResult>
}