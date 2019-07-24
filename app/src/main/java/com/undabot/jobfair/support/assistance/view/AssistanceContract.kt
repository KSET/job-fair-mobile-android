package com.undabot.jobfair.support.assistance.view

import com.undabot.jobfair.core.view.BaseCoordinator
import com.undabot.jobfair.core.view.BasePresenter
import com.undabot.jobfair.support.assistance.usecases.RequestAssistance

interface AssistanceContract {

    interface View {
        fun displayAssistanceSent(note: String?)
        fun displayError()
    }

    interface Presenter : BasePresenter<View>, RequestAssistance.RequestsAssistance

    interface Coordinator : BaseCoordinator<View> {
        fun assistanceRequested(text: String)
    }
}