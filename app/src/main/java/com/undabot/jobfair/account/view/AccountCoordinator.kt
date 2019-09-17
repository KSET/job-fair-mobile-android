package com.undabot.jobfair.account.view

import com.undabot.jobfair.account.usecases.LogoutUser
import com.undabot.jobfair.account.usecases.ShowAccountInfo
import com.undabot.jobfair.account.view.AccountContract.Coordinator
import com.undabot.jobfair.account.view.AccountContract.Presenter
import com.undabot.jobfair.account.view.AccountContract.View
import com.undabot.jobfair.core.view.AbsCoordinator
import javax.inject.Inject

class AccountCoordinator @Inject constructor(
    presenter: Presenter,
    private val signOutUser: LogoutUser,
    private val showAccountInfo: ShowAccountInfo
) : AbsCoordinator<View, Presenter>(presenter), Coordinator {

    override fun signOutRequested() {
        signOutUser(presenter)
    }

    override fun accountInfoRequested() {
        showAccountInfo(presenter)
    }
}