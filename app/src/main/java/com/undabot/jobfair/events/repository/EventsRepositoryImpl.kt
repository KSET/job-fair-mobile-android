package com.undabot.jobfair.events.repository

import com.undabot.jobfair.core.schedulers.WorkerScheduler
import com.undabot.jobfair.events.entities.Event
import com.undabot.jobfair.events.repository.mappers.PresentationMapper
import com.undabot.jobfair.events.repository.mappers.WorkshopMapper
import com.undabot.jobfair.networking.services.ResourceApiService
import io.reactivex.Single

class EventsRepositoryImpl constructor(
        private val eventType: Event.Type = Event.Type.PRESENTATION,
        private val resourceApiService: ResourceApiService,
        private val presentationMapper: PresentationMapper,
        private val workshopMapper: WorkshopMapper,
        private val workerScheduler: WorkerScheduler
) : EventsRepository {

    override fun getEvents(): Single<EventResult> =
            when (eventType) {
                Event.Type.PRESENTATION -> presentationsResult()
                Event.Type.WORKSHOP -> workshopsResult()
            }.subscribeOn(workerScheduler.get())

    private fun presentationsResult(): Single<EventResult> =
            resourceApiService.presentations()
                    .map { list -> EventResult(list.map { presentationMapper.map(it) }) }

    private fun workshopsResult(): Single<EventResult> =
            resourceApiService.workshops()
                    .map { list -> EventResult(list.map { workshopMapper.map(it) }) }
}