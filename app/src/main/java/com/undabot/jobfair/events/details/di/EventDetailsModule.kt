package com.undabot.jobfair.events.details.di

import com.undabot.jobfair.core.di.scope.PerFragment
import com.undabot.jobfair.events.details.view.EventDetailsContainerContract
import com.undabot.jobfair.events.details.view.EventDetailsContainerCoordinator
import com.undabot.jobfair.events.details.view.EventDetailsContainerPresenter
import com.undabot.jobfair.events.details.view.EventDetailsContract
import com.undabot.jobfair.events.details.view.EventDetailsCoordinator
import com.undabot.jobfair.events.details.view.EventDetailsPresenter
import dagger.Module
import dagger.Provides

@Module
class EventDetailsModule {

    @Provides
    @PerFragment
    fun coordinator(coordinator: EventDetailsContainerCoordinator): EventDetailsContainerContract.Coordinator =
        coordinator

    @Provides
    @PerFragment
    fun presenter(presenter: EventDetailsContainerPresenter): EventDetailsContainerContract.Presenter =
        presenter

    @Provides
    @PerFragment
    fun detailsCoordinator(coordinator: EventDetailsCoordinator): EventDetailsContract.Coordinator =
        coordinator

    @Provides
    @PerFragment
    fun detailsPresenter(presenter: EventDetailsPresenter): EventDetailsContract.Presenter = presenter
}