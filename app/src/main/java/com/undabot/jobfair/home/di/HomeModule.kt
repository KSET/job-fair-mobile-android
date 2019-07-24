package com.undabot.jobfair.home.di

import com.undabot.jobfair.core.di.scope.PerActivity
import com.undabot.jobfair.home.view.HomeContract
import com.undabot.jobfair.home.view.HomeCoordinator
import com.undabot.jobfair.home.view.HomePresenter
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @Provides
    @PerActivity
    fun coordinator(coordinator: HomeCoordinator): HomeContract.Coordinator = coordinator

    @Provides
    @PerActivity
    fun presenter(presenter: HomePresenter): HomeContract.Presenter = presenter
}