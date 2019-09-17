package com.undabot.jobfair.support.submitcv.view

import com.undabot.jobfair.core.view.AbsCoordinator
import com.undabot.jobfair.support.submitcv.usecase.GetToken
import javax.inject.Inject

class SubmitCvCoordinator @Inject constructor(
    presenter: SubmitCvContract.Presenter,
    private val getToken: GetToken
) : AbsCoordinator<SubmitCvContract.View, SubmitCvContract.Presenter>(presenter),
    SubmitCvContract.Coordinator {
    override fun tokenRequested() = getToken(presenter)
}