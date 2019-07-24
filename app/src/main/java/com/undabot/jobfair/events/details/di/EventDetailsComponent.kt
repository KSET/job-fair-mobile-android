package com.undabot.jobfair.events.details.di

import com.undabot.jobfair.core.di.scope.PerFragment
import com.undabot.jobfair.events.details.view.EventDetailsContainerScreen
import com.undabot.jobfair.events.details.view.EventDetailsFragment
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = [EventDetailsModule::class])
interface EventDetailsComponent {

    fun inject(activity: EventDetailsContainerScreen)
    fun inject(fragment: EventDetailsFragment)
}