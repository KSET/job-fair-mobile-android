package com.undabot.jobfair.splash.view

import com.undabot.jobfair.core.view.BaseCoordinator
import com.undabot.jobfair.core.view.BasePresenter
import com.undabot.jobfair.splash.usecases.InitializeApp

interface SplashContract {

    interface Coordinator : BaseCoordinator<View> {
        fun initializeApp()
        fun requestLogin()
        fun requestSkipLogin()
    }

    interface Presenter : BasePresenter<View>, InitializeApp.InitializesApp {
        fun openLoginScreen()
    }

    interface View {
        fun openLoginScreen()
        fun openHomeScreen()
        fun showLoginTeaser()
    }
}