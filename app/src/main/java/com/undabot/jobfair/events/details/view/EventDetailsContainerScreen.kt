package com.undabot.jobfair.events.details.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.undabot.jobfair.R
import com.undabot.jobfair.companies.details.view.CompanyDetailsContainerScreen
import com.undabot.jobfair.companies.view.models.CompanyViewModel
import com.undabot.jobfair.core.di.ApplicationComponent
import com.undabot.jobfair.core.view.BaseActivity
import com.undabot.jobfair.events.calendar.CalendarInfo
import com.undabot.jobfair.events.details.di.EventDetailsModule
import com.undabot.jobfair.events.view.EventViewModel
import com.undabot.jobfair.utils.CalendarUtils
import com.undabot.jobfair.utils.dpToPx
import kotlinx.android.synthetic.main.event_details_activity.*
import kotlinx.android.synthetic.main.navigatable_pager.view.*
import kotlinx.android.synthetic.main.toolbar_details.*
import javax.inject.Inject

class EventDetailsContainerScreen : BaseActivity(), EventDetailsContainerContract.View {

    @Inject lateinit var coordinator: EventDetailsContainerContract.Coordinator

    private lateinit var toolbar: Toolbar

    private var events: List<EventViewModel> = arrayListOf()

    companion object {
        private const val TITLE_PADDING_WITH_MENU = 36 // dp
        private const val TITLE_PADDING_WITHOUT_MENU = 86 // dp

        private const val EXTRA_PARAMS = "extra_params"

        fun intent(
            context: Context,
            params: EventDetailsParams
        ): Intent {
            val intent = Intent(context, EventDetailsContainerScreen::class.java)
            intent.putExtra(EXTRA_PARAMS, params)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_details_activity)
        toolbar = findViewById(R.id.toolbar)
        coordinator.bind(this)
        val params = intent.getParcelableExtra<EventDetailsParams>(EXTRA_PARAMS)
        coordinator.openedWith(params)

        setSupportActionBar(toolbar)
        toolbarTitle.text = getString(R.string.agenda)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { super.onBackPressed() }
        toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.action_calendar) {
                onAddToCalendarPressed(currentPageCalendarInfo())
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.calendar, menu)
        updateToolbarActions()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_calendar -> {
                onAddToCalendarPressed(currentPageCalendarInfo())
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun show(events: List<EventViewModel>) {
        this.events = events
        viewPager.pager.adapter = PresentationDetailsPagerAdapter(supportFragmentManager, events)
        viewPager.viewPager.clearOnPageChangeListeners()
        viewPager.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                updateToolbarActions()
            }
        })
    }

    override fun showEventAt(index: Int) {
        viewPager.viewPager.currentItem = index
        updateToolbarActions()
    }

    override fun openCompanyDetailsFor(company: CompanyViewModel) {
        CompanyDetailsContainerScreen.startWith(this, arrayListOf(company))
    }

    override fun addToCalendar(calendarInfo: CalendarInfo) =
        CalendarUtils.addToCalendar(calendarInfo, this)

    internal fun onOpenCompanyDetailsPressed(company: CompanyViewModel) =
        coordinator.openCompanyDetailsPressedFor(company)

    private fun onAddToCalendarPressed(calendarInfo: CalendarInfo) =
        coordinator.addToCalendarPressedFor(calendarInfo)

    override fun injectToAppComponent(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(EventDetailsModule()).inject(this)
    }

    private fun updateToolbarActions() {
        val eventInPast = events[viewPager.viewPager.currentItem].inPast
        toolbar.menu?.findItem(R.id.action_calendar)?.isVisible = !eventInPast
        toolbarTitle.setPadding(
            toolbarTitle.paddingLeft,
            toolbarTitle.paddingTop,
            getRightToolbarTitlePaddingFor(eventInPast),
            toolbar.paddingBottom
        )
    }

    private fun getRightToolbarTitlePaddingFor(eventInPast: Boolean): Int = if (eventInPast) {
        TITLE_PADDING_WITHOUT_MENU.dpToPx(this)
    } else {
        TITLE_PADDING_WITH_MENU.dpToPx(this)
    }

    private fun currentPageCalendarInfo() = events[viewPager.viewPager.currentItem].calendarInfo

    class PresentationDetailsPagerAdapter(
        fragmentManager: FragmentManager,
        val list: List<EventViewModel>
    ) : FragmentStatePagerAdapter(fragmentManager) {

        override fun getItem(position: Int): Fragment = EventDetailsFragment.newInstance(list[position])

        override fun getCount(): Int = list.size
    }
}