package com.undabot.jobfair.account.view

import com.undabot.jobfair.account.usecases.LogoutUser
import com.undabot.jobfair.account.usecases.ShowAccountInfo
import com.undabot.jobfair.account.view.model.AccountViewModel
import com.undabot.jobfair.core.view.BaseCoordinator
import com.undabot.jobfair.core.view.BasePresenter

interface AccountContract {

    interface View {
        fun restartApplication()
        fun show(viewModels: List<AccountViewModel>)
    }

    interface Presenter : BasePresenter<View>,
        LogoutUser.LogoutsUser,
        ShowAccountInfo.ShowsAccountInfo

    interface Coordinator : BaseCoordinator<View> {
        fun signOutRequested()
        fun accountInfoRequested()
    }
}