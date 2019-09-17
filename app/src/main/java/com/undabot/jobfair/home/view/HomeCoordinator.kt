package com.undabot.jobfair.home.view

import com.undabot.jobfair.core.view.AbsCoordinator
import com.undabot.jobfair.home.usecases.GetLoggedInUser
import com.undabot.jobfair.home.usecases.ShowAccount
import javax.inject.Inject

class HomeCoordinator @Inject constructor(
    presenter: HomeContract.Presenter,
    private val getLoggedInUser: GetLoggedInUser,
    private val showAccount: ShowAccount
) : AbsCoordinator<HomeContract.View, HomeContract.Presenter>(presenter),
    HomeContract.Coordinator {

    override fun requestLoggedInUser() {
        getLoggedInUser(presenter)
    }

    override fun accountPressed() {
        showAccount(presenter)
    }
}
