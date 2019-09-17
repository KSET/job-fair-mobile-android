package com.undabot.jobfair.support.submitcv.di

import com.undabot.jobfair.core.di.scope.PerActivity
import com.undabot.jobfair.support.repository.SupportRepository
import com.undabot.jobfair.support.repository.SupportRepositoryImpl
import com.undabot.jobfair.support.submitcv.view.SubmitCvContract
import com.undabot.jobfair.support.submitcv.view.SubmitCvCoordinator
import com.undabot.jobfair.support.submitcv.view.SubmitCvPresenter
import dagger.Module
import dagger.Provides

@Module
class SubmitCvModule {

    @Provides
    @PerActivity
    fun coordinator(coordinator: SubmitCvCoordinator): SubmitCvContract.Coordinator = coordinator

    @Provides
    @PerActivity
    fun presenter(presenter: SubmitCvPresenter): SubmitCvContract.Presenter = presenter

    @Provides
    @PerActivity
    fun repository(repository: SupportRepositoryImpl): SupportRepository = repository
}