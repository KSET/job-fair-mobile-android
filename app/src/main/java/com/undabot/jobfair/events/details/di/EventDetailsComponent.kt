package com.undabot.jobfair.events.details.di

import com.undabot.jobfair.core.di.scope.PerFragment
import com.undabot.jobfair.events.details.view.EventDetailsScreen
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = [EventDetailsModule::class])
interface EventDetailsComponent {

    fun inject(activity: EventDetailsScreen)
}