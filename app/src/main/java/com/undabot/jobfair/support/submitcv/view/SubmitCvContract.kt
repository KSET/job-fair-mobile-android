package com.undabot.jobfair.support.submitcv.view

import com.undabot.jobfair.core.view.BaseCoordinator
import com.undabot.jobfair.core.view.BasePresenter
import com.undabot.jobfair.support.submitcv.usecase.GetToken

interface SubmitCvContract {

    interface View {
        fun showEditResumeWebPage(token: String)
        fun showCreateResumeWebPage()
        fun showError()
    }

    interface Coordinator : BaseCoordinator<View> {
        fun tokenRequested()
    }

    interface Presenter : BasePresenter<View>,
        GetToken.GetsToken
}