package com.undabot.jobfair.home.di

import com.undabot.jobfair.core.di.scope.PerActivity
import com.undabot.jobfair.home.view.MainActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [HomeModule::class])
interface HomeComponent {

    fun inject(activity: MainActivity)
}