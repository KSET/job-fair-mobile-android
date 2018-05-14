package com.undabot.jobfair.about.di

import com.undabot.jobfair.about.view.AboutScreen
import com.undabot.jobfair.core.di.scope.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [AboutModule::class])
interface AboutComponent {

    fun inject(activity: AboutScreen)
}