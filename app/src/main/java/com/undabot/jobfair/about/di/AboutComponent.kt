package com.undabot.jobfair.about.di

import com.undabot.jobfair.about.view.AboutScreen
import com.undabot.jobfair.core.di.scope.PerFragment
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = [AboutModule::class])
interface AboutComponent {

    fun inject(activity: AboutScreen)
}