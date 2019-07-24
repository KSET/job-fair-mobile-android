package com.undabot.jobfair.splash.view

import com.undabot.jobfair.core.view.AbsPresenter
import javax.inject.Inject

class SplashPresenter @Inject constructor()
    : AbsPresenter<SplashContract.View>(), SplashContract.Presenter {
    override fun openLoginScreen() = onView {
        it.openLoginScreen()
    }

    override fun openHomeScreen() = onView {
        it.openHomeScreen()
    }

    override fun showLoginTeaser() = onView {
        it.showLoginTeaser()
    }
}