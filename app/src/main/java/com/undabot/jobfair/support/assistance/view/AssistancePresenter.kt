package com.undabot.jobfair.support.assistance.view

import com.undabot.jobfair.core.view.AbsPresenter
import javax.inject.Inject

class AssistancePresenter @Inject constructor() : AbsPresenter<AssistanceContract.View>(), AssistanceContract.Presenter {

    override fun assistanceSent(note: String?) = onView {
        it.displayAssistanceSent(note)
    }

    override fun assistanceError() = onView {
        it.displayError()
    }
}