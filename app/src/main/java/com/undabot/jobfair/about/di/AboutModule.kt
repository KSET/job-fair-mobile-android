package com.undabot.jobfair.about.di

import com.undabot.jobfair.about.view.AboutContract
import com.undabot.jobfair.about.view.AboutCoordinator
import com.undabot.jobfair.about.view.AboutPresenter
import com.undabot.jobfair.core.di.scope.PerFragment
import dagger.Module
import dagger.Provides

@Module
class AboutModule {

    @Provides
    @PerFragment
    fun coordinator(coordinator: AboutCoordinator): AboutContract.Coordinator = coordinator

    @Provides
    @PerFragment
    fun presenter(presenter: AboutPresenter): AboutContract.Presenter = presenter
}