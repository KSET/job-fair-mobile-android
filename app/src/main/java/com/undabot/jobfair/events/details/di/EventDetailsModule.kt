package com.undabot.jobfair.events.details.di

import com.undabot.jobfair.core.di.scope.PerFragment
import com.undabot.jobfair.events.details.view.EventDetailsContract
import com.undabot.jobfair.events.details.view.EventDetailsCoordinator
import com.undabot.jobfair.events.details.view.EventDetailsPresenter
import dagger.Module
import dagger.Provides

@Module
class EventDetailsModule {

    @Provides
    @PerFragment
    fun coordinator(coordinator: EventDetailsCoordinator): EventDetailsContract.Coordinator =
            coordinator

    @Provides
    @PerFragment
    fun presenter(presenter: EventDetailsPresenter): EventDetailsContract.Presenter =
            presenter
}