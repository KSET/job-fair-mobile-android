package com.undabot.jobfair.events.details.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.widget.Toolbar
import com.undabot.jobfair.R
import com.undabot.jobfair.companies.details.view.CompanyDetailsContainerScreen
import com.undabot.jobfair.companies.view.models.CompanyViewModel
import com.undabot.jobfair.core.di.ApplicationComponent
import com.undabot.jobfair.core.view.BaseActivity
import com.undabot.jobfair.events.calendar.CalendarInfo
import com.undabot.jobfair.events.details.di.EventDetailsModule
import com.undabot.jobfair.events.entities.Event
import com.undabot.jobfair.events.view.EventViewModel
import com.undabot.jobfair.utils.CalendarUtils
import kotlinx.android.synthetic.main.event_details_activity.*
import kotlinx.android.synthetic.main.navigatable_pager.view.*
import javax.inject.Inject

class EventDetailsScreen : BaseActivity(), EventDetailsContract.View {

    @Inject lateinit var coordinator: EventDetailsContract.Coordinator

    private lateinit var toolbar: Toolbar

    companion object {
        private val EXTRA_PARAMS = "extra_params"

        fun intent(
                context: Context,
                params: EventDetailsParams
        ): Intent {
            val intent = Intent(context, EventDetailsScreen::class.java)
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
        supportActionBar?.title = titleFor(params)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { super.onBackPressed() }
    }

    override fun show(events: List<EventViewModel>) {
        viewPager.pager.adapter = PresentationDetailsPagerAdapter(supportFragmentManager, events)
    }

    override fun showEventAt(index: Int) {
        viewPager.pager.currentItem = index
    }

    override fun openCompanyDetailsFor(company: CompanyViewModel) {
        CompanyDetailsContainerScreen.startWith(this, arrayListOf(company))
    }

    override fun addToCalendar(calendarInfo: CalendarInfo) =
            CalendarUtils.addToCalendar(calendarInfo, this)

    internal fun onOpenCompanyDetailsPressed(company: CompanyViewModel) =
            coordinator.openCompanyDetailsPressedFor(company)

    internal fun onAddToCalendarPressed(calendarInfo: CalendarInfo) =
            coordinator.addToCalendarPressedFor(calendarInfo)

    override fun injectToAppComponent(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(EventDetailsModule()).inject(this)
    }

    private fun titleFor(params: EventDetailsParams) =
            when (params.eventType) {
                Event.Type.PRESENTATION -> getString(R.string.presentations)
                else -> getString(R.string.workshops)
            }

    class PresentationDetailsPagerAdapter(
            fragmentManager: FragmentManager,
            val list: List<EventViewModel>
    ) : FragmentStatePagerAdapter(fragmentManager) {

        override fun getItem(position: Int): Fragment =
                EventDetailsFragment.newInstance(list[position])

        override fun getCount(): Int = list.size
    }
}