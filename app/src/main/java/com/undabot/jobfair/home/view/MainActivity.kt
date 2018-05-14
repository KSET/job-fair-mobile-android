package com.undabot.jobfair.home.view

import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.undabot.jobfair.R
import com.undabot.jobfair.about.view.AboutScreen
import com.undabot.jobfair.booths.view.BoothsScreen
import com.undabot.jobfair.companies.list.view.CompaniesScreen
import com.undabot.jobfair.core.di.ApplicationComponent
import com.undabot.jobfair.core.view.BaseActivity
import com.undabot.jobfair.events.entities.Event
import com.undabot.jobfair.events.list.view.EventsScreen
import com.undabot.jobfair.home.adapters.HomePagerAdapter
import com.undabot.jobfair.news.list.view.NewsScreen
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main.*

class MainActivity : BaseActivity() {

    private object Screens {
        const val NEWS = 0
        const val PRESENTATIONS = 1
        const val WORKSHOPS = 2
        const val COMPANIES = 3
        const val MAP = 4
    }

    private val fragments = listOf(
            NewsScreen(),
            EventsScreen.newInstance(Event.Type.PRESENTATION),
            EventsScreen.newInstance(Event.Type.WORKSHOP),
            CompaniesScreen(),
            BoothsScreen())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar.inflateMenu(R.menu.info_and_filter)
        toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.action_info) {
                AboutScreen.startWith(this)
            }
            fragments[viewpager.currentItem].onMenuItemClick(it)
        }

        setToolbarTitleFor(Screens.NEWS)
        setToolbarMenuFor(Screens.NEWS)

        viewpager.adapter = HomePagerAdapter(
                fragments, supportFragmentManager)
        viewpager.offscreenPageLimit = 4

        bottomNavigation.addItems(
                listOf(AHBottomNavigationItem(getString(R.string.news), R.drawable.ic_news_background),
                        AHBottomNavigationItem(getString(R.string.presentations), R.drawable.ic_presentations_background),
                        AHBottomNavigationItem(getString(R.string.workshops), R.drawable.ic_workshops_background),
                        AHBottomNavigationItem(getString(R.string.companies), R.drawable.ic_companies_background),
                        AHBottomNavigationItem(getString(R.string.booths), R.drawable.ic_booths_background))
        )

        bottomNavigation.setOnTabSelectedListener { position, wasSelected ->
            if (!wasSelected) {
                viewpager.currentItem = position
                setToolbarTitleFor(position)
                setToolbarMenuFor(position)
            }
            true
        }

        bottomNavigation.defaultBackgroundColor = Color.WHITE
        bottomNavigation.accentColor = ContextCompat.getColor(this, R.color.colorPrimary)
        bottomNavigation.inactiveColor = ContextCompat.getColor(this, R.color.colorAccent)
        bottomNavigation.isForceTint = false
    }

    private fun setToolbarMenuFor(position: Int) {
        when (position) {
            Screens.COMPANIES -> toolbar.menu.findItem(R.id.action_filter).isVisible = true
            else -> toolbar.menu.findItem(R.id.action_filter).isVisible = false
        }
    }

    private fun setToolbarTitleFor(position: Int) {
        val titleText: String = when (position) {
            Screens.NEWS -> getString(R.string.news)
            Screens.PRESENTATIONS -> getString(R.string.presentations)
            Screens.WORKSHOPS -> getString(R.string.workshops)
            Screens.COMPANIES -> getString(R.string.companies)
            else -> getString(R.string.booths)
        }
        toolbar.title = titleText
    }

    override fun injectToAppComponent(applicationComponent: ApplicationComponent) {
        // Nothing to inject
    }
}
