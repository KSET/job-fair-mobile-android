package com.undabot.jobfair.home.view

import com.undabot.jobfair.core.view.AbsPresenter
import com.undabot.jobfair.login.models.User
import javax.inject.Inject

class HomePresenter @Inject constructor()
    : AbsPresenter<HomeContract.View>(), HomeContract.Presenter {

    override fun showOptionsForUser(user: User?) = onView {
        it.showOptionsForUser(user)
    }

    override fun showLoginScreen() = onView {
        it.openLoginScreen()
    }

    override fun showAccountScreen() = onView {
        it.openAccountScreen()
    }
}