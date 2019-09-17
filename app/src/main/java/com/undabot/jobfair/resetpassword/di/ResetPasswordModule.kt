package com.undabot.jobfair.resetpassword.di

import com.undabot.jobfair.core.di.scope.PerActivity
import com.undabot.jobfair.resetpassword.view.ResetPasswordContract
import com.undabot.jobfair.resetpassword.view.ResetPasswordCoordinator
import com.undabot.jobfair.resetpassword.view.ResetPasswordPresenter
import dagger.Module
import dagger.Provides

@Module
class ResetPasswordModule {

    @Provides
    @PerActivity
    fun coordinator(coordinator: ResetPasswordCoordinator): ResetPasswordContract.Coordinator = coordinator

    @Provides
    @PerActivity
    fun presenter(presenter: ResetPasswordPresenter): ResetPasswordContract.Presenter = presenter
}