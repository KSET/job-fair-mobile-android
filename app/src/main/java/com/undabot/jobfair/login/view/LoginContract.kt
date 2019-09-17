package com.undabot.jobfair.login.view

import com.undabot.jobfair.core.view.BaseCoordinator
import com.undabot.jobfair.core.view.BasePresenter
import com.undabot.jobfair.login.usecases.LoginUser

interface LoginContract {

    interface Coordinator : BaseCoordinator<View> {
        fun loginRequested(email: String, password: String)
    }

    interface Presenter : BasePresenter<View>, LoginUser.DisplaysHomeScreen

    interface View {
        fun openHomeScreen()
        fun showGeneralError()
    }
}