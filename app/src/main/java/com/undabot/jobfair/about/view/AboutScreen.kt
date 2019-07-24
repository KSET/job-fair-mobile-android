package com.undabot.jobfair.about.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.undabot.jobfair.R
import com.undabot.jobfair.about.di.AboutModule
import com.undabot.jobfair.core.di.ApplicationComponent
import com.undabot.jobfair.core.view.BaseFragment
import com.undabot.jobfair.utils.canHandle
import kotlinx.android.synthetic.main.about_screen_layout.*
import javax.inject.Inject

class AboutScreen : BaseFragment(), AboutContract.View {

    companion object {
        fun newInstance() = AboutScreen()
    }

    @Inject lateinit var coordinator: AboutContract.Coordinator

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setClickListeners()
        coordinator.bind(this)
        timeLabel.text = getText(R.string.about_screen_time_info)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.about_screen, container, false)
    }

    override fun onDestroy() {
        coordinator.unbind(this)
        super.onDestroy()
    }

    override fun openUrl(url: String) = openIntent(Intent(Intent.ACTION_VIEW, Uri.parse(url)))

    override fun shareUrl(url: String) {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT, url)
        intent.type = "text/plain"
        openIntent(intent)
    }

    private fun openIntent(intent: Intent) =
        when (context?.canHandle(intent)) {
            true -> startActivity(intent)
            else -> showGeneralErrorMessage()
        }

    private fun setClickListeners() {
        webButton.setOnClickListener { coordinator.onWebPressed() }
        emailButton.setOnClickListener { coordinator.onEmailPressed() }
        facebookButton.setOnClickListener { coordinator.onFacebookPressed() }
        instagramButton.setOnClickListener { coordinator.onInstagramPressed() }
        youtubeButton.setOnClickListener { coordinator.onYouTubeChannelPressed() }
        mapsButton.setOnClickListener { coordinator.onMapPressed() }
        dayOneStreamButton.setOnClickListener { coordinator.onDayOneStreamPressed() }
        dayTwoStreamButton.setOnClickListener { coordinator.onDayTwoStreamPressed() }
        developerButton.setOnClickListener { coordinator.onDeveloperInfoPressed() }
    }

    override fun injectToAppComponent(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(AboutModule()).inject(this)
    }
}