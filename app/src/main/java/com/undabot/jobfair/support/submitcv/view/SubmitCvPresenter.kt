package com.undabot.jobfair.support.submitcv.view

import com.undabot.jobfair.core.view.AbsPresenter
import javax.inject.Inject

class SubmitCvPresenter @Inject constructor() : AbsPresenter<SubmitCvContract.View>(),
    SubmitCvContract.Presenter {

    override fun tokenRequestSuccess(token: String) = onView {
        it.showEditResumeWebPage(token)
    }

    override fun tokenRequestError() = onView {
        it.showCreateResumeWebPage()
    }
}