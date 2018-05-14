package com.undabot.jobfair.about.view

import com.undabot.jobfair.core.view.AbsPresenter
import javax.inject.Inject

class AboutPresenter
@Inject constructor() : AbsPresenter<AboutContract.View>(), AboutContract.Presenter {

    override fun openUrl(url: String) = onView { it.openUrl(url) }

    override fun shareUrl(url: String) = onView { it.shareUrl(url) }
}