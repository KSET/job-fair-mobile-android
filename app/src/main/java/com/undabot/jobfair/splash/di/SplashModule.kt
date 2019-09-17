package com.undabot.jobfair.splash.di

import com.undabot.jobfair.core.di.scope.PerActivity
import com.undabot.jobfair.splash.view.SplashContract
import com.undabot.jobfair.splash.view.SplashCoordinator
import com.undabot.jobfair.splash.view.SplashPresenter
import dagger.Module
import dagger.Provides

@Module
class SplashModule {

    @Provides
    @PerActivity
    fun coordinator(coordinator: SplashCoordinator): SplashContract.Coordinator = coordinator

    @Provides
    @PerActivity
    fun presenter(presenter: SplashPresenter): SplashContract.Presenter = presenter
}