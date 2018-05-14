package com.undabot.jobfair.about.view

import com.undabot.jobfair.about.Links
import com.undabot.jobfair.about.view.AboutContract.Coordinator
import com.undabot.jobfair.about.view.AboutContract.Presenter
import com.undabot.jobfair.about.view.AboutContract.View
import com.undabot.jobfair.core.view.AbsCoordinator
import javax.inject.Inject

class AboutCoordinator
@Inject constructor(
        presenter: Presenter
) : AbsCoordinator<View, Presenter>(presenter), Coordinator {

    override fun onMapPressed() = presenter.openUrl(Links.MAP)

    override fun onWebPressed() = presenter.openUrl(Links.WEB)

    override fun onEmailPressed() = presenter.openUrl(Links.EMAIL)

    override fun onFacebookPressed() = presenter.openUrl(Links.FACEBOOK)

    override fun onInstagramPressed() = presenter.openUrl(Links.INSTAGRAM)

    override fun onYouTubeChannelPressed() = presenter.openUrl(Links.YOUTUBE)

    override fun onDayOneStreamPressed() = presenter.openUrl(Links.DAY_ONE_STREAM)

    override fun onDayTwoStreamPressed() = presenter.openUrl(Links.DAY_TWO_STREAM)

    override fun onSharePressed() = presenter.shareUrl(Links.WEB)

    override fun onDeveloperInfoPressed() = presenter.openUrl(Links.DEVELOPER_WEB)
}