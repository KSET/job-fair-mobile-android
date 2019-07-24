package com.undabot.jobfair.login.di

import com.undabot.jobfair.core.di.scope.PerActivity
import com.undabot.jobfair.login.view.LoginContract
import com.undabot.jobfair.login.view.LoginCoordinator
import com.undabot.jobfair.login.view.LoginPresenter
import dagger.Module
import dagger.Provides

@Module
class LoginModule {

    @Provides
    @PerActivity
    fun coordinator(coordinator: LoginCoordinator): LoginContract.Coordinator = coordinator

    @Provides
    @PerActivity
    fun presenter(presenter: LoginPresenter): LoginContract.Presenter = presenter
}