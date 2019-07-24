package com.undabot.jobfair.events.list.view

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.undabot.jobfair.R
import com.undabot.jobfair.core.di.ApplicationComponent
import com.undabot.jobfair.core.view.BaseFragment
import com.undabot.jobfair.events.entities.Event
import com.undabot.jobfair.events.list.di.EventsModule
import com.undabot.jobfair.events.view.EventsViewModel
import com.undabot.jobfair.utils.setupWithViewPager
import kotlinx.android.synthetic.main.events_screen.*
import java.util.*
import javax.inject.Inject

class EventsScreen : BaseFragment(), EventsContract.View {

    @Inject lateinit var coordinator: EventsContract.Coordinator

    companion object {
        private const val EXTRA_EVENT_TYPE = "extra_event_type"

        fun newInstance(eventType: Event.Type): EventsScreen =
            EventsScreen().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_EVENT_TYPE, eventType.name)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.events_screen, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        swipeRefresh.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark)
        swipeRefresh.setOnRefreshListener { coordinator.eventsRequested() }

        coordinator.bind(this)
        coordinator.eventsRequested()
    }

    override fun onDestroyView() {
        coordinator.unbind(this)
        super.onDestroyView()
    }

    override fun showGeneralErrorMessage() {
        super.showGeneralErrorMessage()
        swipeRefresh.isRefreshing = false
    }

    override fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun showEvents(eventsViewModel: EventsViewModel) {
        viewPager.adapter = EventsPagerAdapter(
            context!!,
            eventsViewModel,
            eventTypeFromArguments(), childFragmentManager
        )
        viewPager.offscreenPageLimit = 2
        tweakViewPagerBehaviourWithSwipeRefresh()
        swipeRefresh.isRefreshing = false

        tabLayout.setupWithViewPager(
            viewPager = viewPager,
            marginBetween = 8
        )
    }

    private fun tweakViewPagerBehaviourWithSwipeRefresh() {
        viewPager.setOnTouchListener { _, event ->
            swipeRefresh.isEnabled = false
            if (event.action == MotionEvent.ACTION_UP) {
                swipeRefresh.isEnabled = true
            }
            false
        }
    }

    override fun injectToAppComponent(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(EventsModule(eventTypeFromArguments())).inject(this)
    }

    private fun eventTypeFromArguments(): Event.Type =
        Event.Type.valueOf(arguments?.getString(
            EXTRA_EVENT_TYPE, Event.Type.PRESENTATION.name).orEmpty())

    class EventsPagerAdapter(
        private val context: Context,
        private val eventsViewModel: EventsViewModel,
        private val eventType: Event.Type,
        fragmentManager: FragmentManager
    ) : FragmentStatePagerAdapter(fragmentManager) {

        @Suppress("UNCHECKED_CAST")
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> EventsListFragment.newInstance(eventsViewModel.firstDay as ArrayList<Parcelable>, eventType)
                else -> EventsListFragment.newInstance(eventsViewModel.secondDay as ArrayList<Parcelable>, eventType)
            }
        }

        override fun getPageTitle(position: Int): String = when (position) {
            0 -> context.getString(R.string.events_screen_day_one_tab_name)
            else -> context.getString(R.string.events_screen_day_two_tab_name)
        }

        override fun getCount() = 2
    }
}