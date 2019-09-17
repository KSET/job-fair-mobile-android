package com.undabot.jobfair.account.di

import com.undabot.jobfair.account.view.AccountContract
import com.undabot.jobfair.account.view.AccountCoordinator
import com.undabot.jobfair.account.view.AccountPresenter
import com.undabot.jobfair.core.di.scope.PerActivity
import dagger.Module
import dagger.Provides

@Module
class AccountModule {

    @Provides
    @PerActivity
    fun coordinator(coordinator: AccountCoordinator): AccountContract.Coordinator = coordinator

    @Provides
    @PerActivity
    fun presenter(presenter: AccountPresenter): AccountContract.Presenter = presenter
}