package com.undabot.jobfair.home.view

import com.undabot.jobfair.core.view.BaseCoordinator
import com.undabot.jobfair.core.view.BasePresenter
import com.undabot.jobfair.home.usecases.GetLoggedInUser
import com.undabot.jobfair.home.usecases.ShowAccount
import com.undabot.jobfair.login.models.User

interface HomeContract {

    interface Coordinator : BaseCoordinator<View> {
        fun requestLoggedInUser()
        fun accountPressed()
    }

    interface Presenter : BasePresenter<View>,
        GetLoggedInUser.GetsLoggedInUser,
        ShowAccount.ShowsAccount

    interface View {
        fun showOptionsForUser(user: User?)
        fun openLoginScreen()
        fun openAccountScreen()
    }
}