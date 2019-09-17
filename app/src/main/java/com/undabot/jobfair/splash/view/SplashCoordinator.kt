package com.undabot.jobfair.splash.view

import com.undabot.jobfair.core.view.AbsCoordinator
import com.undabot.jobfair.splash.usecases.InitializeApp
import javax.inject.Inject

class SplashCoordinator @Inject constructor(
    presenter: SplashContract.Presenter,
    private val initializeApp: InitializeApp
) : AbsCoordinator<SplashContract.View, SplashContract.Presenter>(presenter),
    SplashContract.Coordinator {
    override fun initializeApp() {
        initializeApp.invoke(presenter)
    }

    override fun requestLogin() {
        presenter.openLoginScreen()
    }

    override fun requestSkipLogin() {
        presenter.openHomeScreen()
    }
}
