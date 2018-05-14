package com.undabot.jobfair.booths.di

import com.undabot.jobfair.booths.view.BoothsScreen
import com.undabot.jobfair.core.di.scope.PerFragment
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = [BoothsModule::class])
interface BoothsComponent {

    fun inject(fragment: BoothsScreen)
}