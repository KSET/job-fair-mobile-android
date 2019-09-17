package com.undabot.jobfair.support.assistance.view

import com.undabot.jobfair.core.view.AbsCoordinator
import com.undabot.jobfair.support.assistance.usecases.RequestAssistance
import javax.inject.Inject

class AssistanceCoordinator @Inject constructor(
    presenter: AssistanceContract.Presenter,
    private val requestAssistance: RequestAssistance
) : AbsCoordinator<AssistanceContract.View, AssistanceContract.Presenter>(presenter), AssistanceContract.Coordinator {

    override fun assistanceRequested(text: String) {
        requestAssistance(text, presenter)
    }
}