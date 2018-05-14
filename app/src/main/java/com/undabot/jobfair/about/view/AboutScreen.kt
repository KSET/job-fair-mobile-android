package com.undabot.jobfair.about.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.view.Menu
import android.view.MenuItem
import com.undabot.jobfair.R
import com.undabot.jobfair.about.di.AboutModule
import com.undabot.jobfair.core.di.ApplicationComponent
import com.undabot.jobfair.core.view.BaseActivity
import com.undabot.jobfair.utils.canHandle
import kotlinx.android.synthetic.main.about_screen.*
import kotlinx.android.synthetic.main.about_screen_layout.*
import javax.inject.Inject

class AboutScreen : BaseActivity(), AboutContract.View {

    companion object {
        fun startWith(context: Context) {
            context.startActivity(Intent(context, AboutScreen::class.java))
        }
    }

    @Inject lateinit var coordinator: AboutContract.Coordinator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about_screen)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setClickListeners()
        coordinator.bind(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        toolbar.inflateMenu(R.menu.share)
        toolbar.setOnMenuItemClickListener {
            coordinator.onSharePressed()
            true
        }
        appBarLayout.addOnOffsetChangedListener({ _, verticalOffset ->
            when (toolbarIsCollapsed(verticalOffset)) {
                true -> setToolbarContentColorWith(R.color.white)
                false -> setToolbarContentColorWith(R.color.colorPrimary)
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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
            when (canHandle(intent)) {
                true -> startActivity(intent)
                false -> showGeneralErrorMessage()
            }

    private fun setToolbarContentColorWith(@ColorRes id: Int) {
        toolbar.setTitleTextColor(getColorFor(id))
    }

    private fun getColorFor(@ColorRes colorRes: Int) = ContextCompat.getColor(this, colorRes)

    private fun toolbarIsCollapsed(verticalOffset: Int) =
            collapsingToolbarLayout.height + verticalOffset < 2 * ViewCompat.getMinimumHeight(collapsingToolbarLayout)

    private fun setClickListeners() {
        webButton.setOnClickListener { coordinator.onWebPressed() }
        emailButton.setOnClickListener { coordinator.onEmailPressed() }
        facebookButton.setOnClickListener { coordinator.onFacebookPressed() }
        instagramButton.setOnClickListener { coordinator.onInstagramPressed() }
        youtubeButton.setOnClickListener { coordinator.onYouTubeChannelPressed() }
        locationLabel.setOnClickListener { coordinator.onMapPressed() }
        dayOneStreamButton.setOnClickListener { coordinator.onDayOneStreamPressed() }
        dayTwoStreamButton.setOnClickListener { coordinator.onDayTwoStreamPressed() }
        developerButton.setOnClickListener { coordinator.onDeveloperInfoPressed() }
    }

    override fun injectToAppComponent(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(AboutModule()).inject(this)
    }
}