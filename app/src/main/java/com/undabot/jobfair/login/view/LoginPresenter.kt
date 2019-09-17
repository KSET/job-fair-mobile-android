package com.undabot.jobfair.login.view

import com.undabot.jobfair.core.view.AbsPresenter
import javax.inject.Inject

class LoginPresenter @Inject constructor()
    : AbsPresenter<LoginContract.View>(),
    LoginContract.Presenter {

    override fun showHomeScreen() = onView {
        it.openHomeScreen()
    }

    override fun displayError() = onView {
        it.showGeneralError()
    }
}