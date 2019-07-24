package com.undabot.jobfair.splash.di

import com.undabot.jobfair.core.di.scope.PerActivity
import com.undabot.jobfair.splash.view.SplashActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [SplashModule::class])
interface SplashComponent {

    fun inject(activity: SplashActivity)
}