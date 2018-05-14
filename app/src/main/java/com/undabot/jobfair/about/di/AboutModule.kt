package com.undabot.jobfair.about.di

import com.undabot.jobfair.about.view.AboutContract
import com.undabot.jobfair.about.view.AboutCoordinator
import com.undabot.jobfair.about.view.AboutPresenter
import com.undabot.jobfair.core.di.scope.PerActivity
import dagger.Module
import dagger.Provides

@Module
class AboutModule {

    @Provides
    @PerActivity
    fun coordinator(coordinator: AboutCoordinator): AboutContract.Coordinator = coordinator

    @Provides
    @PerActivity
    fun presenter(presenter: AboutPresenter): AboutContract.Presenter = presenter
}