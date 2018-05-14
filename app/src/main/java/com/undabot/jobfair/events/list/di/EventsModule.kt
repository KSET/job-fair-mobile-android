package com.undabot.jobfair.events.list.di

import com.undabot.jobfair.core.di.scope.PerFragment
import com.undabot.jobfair.core.schedulers.WorkerScheduler
import com.undabot.jobfair.events.entities.Event
import com.undabot.jobfair.events.list.view.EventsContract
import com.undabot.jobfair.events.list.view.EventsCoordinator
import com.undabot.jobfair.events.list.view.EventsPresenter
import com.undabot.jobfair.events.repository.EventsRepository
import com.undabot.jobfair.events.repository.EventsRepositoryImpl
import com.undabot.jobfair.events.repository.mappers.PresentationMapper
import com.undabot.jobfair.events.repository.mappers.WorkshopMapper
import com.undabot.jobfair.networking.services.ResourceApiService
import dagger.Module
import dagger.Provides

@Module
class EventsModule(private val eventType: Event.Type = Event.Type.PRESENTATION) {

    @Provides
    @PerFragment
    fun coordinator(coordinator: EventsCoordinator): EventsContract.Coordinator =
            coordinator

    @Provides
    @PerFragment
    fun presenter(presenter: EventsPresenter): EventsContract.Presenter =
            presenter

    @Provides
    @PerFragment
    fun repository(
            resourceApiService: ResourceApiService,
            workerScheduler: WorkerScheduler
    ): EventsRepository {
        return EventsRepositoryImpl(
                eventType,
                resourceApiService,
                PresentationMapper(),
                WorkshopMapper(),
                workerScheduler)
    }
}