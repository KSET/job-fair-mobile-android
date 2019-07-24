package com.undabot.jobfair.support.drinks.di

import com.undabot.jobfair.core.di.scope.PerActivity
import com.undabot.jobfair.support.drinks.view.DrinksContract
import com.undabot.jobfair.support.drinks.view.DrinksCoordinator
import com.undabot.jobfair.support.drinks.view.DrinksPresenter
import com.undabot.jobfair.support.repository.SupportRepository
import com.undabot.jobfair.support.repository.SupportRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class DrinksModule {

    @Provides
    @PerActivity
    fun coordinator(coordinator: DrinksCoordinator): DrinksContract.Coordinator = coordinator

    @Provides
    @PerActivity
    fun presenter(presenter: DrinksPresenter): DrinksContract.Presenter = presenter

    @Provides
    @PerActivity
    fun repository(repository: SupportRepositoryImpl): SupportRepository = repository
}