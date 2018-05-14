package com.undabot.jobfair.events.list.di

import com.undabot.jobfair.core.di.scope.PerFragment
import com.undabot.jobfair.events.list.view.EventsScreen
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = [EventsModule::class])
interface EventsComponent {

    fun inject(fragment: EventsScreen)
}