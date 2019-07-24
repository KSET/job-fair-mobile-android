package com.undabot.jobfair.support.assistance.di

import com.undabot.jobfair.core.di.scope.PerFragment
import com.undabot.jobfair.support.assistance.view.AssistanceContract
import com.undabot.jobfair.support.assistance.view.AssistanceCoordinator
import com.undabot.jobfair.support.assistance.view.AssistancePresenter
import com.undabot.jobfair.support.repository.SupportRepository
import com.undabot.jobfair.support.repository.SupportRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class AssistanceModule {

    @Provides
    @PerFragment
    fun coordinator(coordinator: AssistanceCoordinator): AssistanceContract.Coordinator = coordinator

    @Provides
    @PerFragment
    fun presenter(presenter: AssistancePresenter): AssistanceContract.Presenter = presenter

    @Provides
    @PerFragment
    fun repository(repository: SupportRepositoryImpl): SupportRepository = repository
}