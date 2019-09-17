package com.undabot.jobfair.events.list.view

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.undabot.jobfair.R
import com.undabot.jobfair.core.di.ApplicationComponent
import com.undabot.jobfair.core.view.BaseFragment
import com.undabot.jobfair.events.details.view.EventDetailsContainerScreen
import com.undabot.jobfair.events.details.view.EventDetailsParams
import com.undabot.jobfair.events.entities.Event
import com.undabot.jobfair.events.list.view.adapter.EventsAdapter
import com.undabot.jobfair.events.view.EventViewModel
import com.undabot.jobfair.utils.CalendarUtils
import kotlinx.android.synthetic.main.event_list_layout.*
import java.util.*

class EventsListFragment : BaseFragment() {

    companion object {
        private val EXTRA_EVENTS = "extra_events"
        private val EXTRA_EVENT_TYPE = "extra_event_type"

        fun newInstance(eventList: ArrayList<Parcelable>,
                        eventType: Event.Type): EventsListFragment =
                EventsListFragment().apply {
                    arguments = Bundle().apply {
                        putParcelableArrayList(EXTRA_EVENTS, eventList)
                        putString(EXTRA_EVENT_TYPE, eventType.name)
                    }
                }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(context)
        updateEvents(eventList())
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.event_list_layout, container, false)
    }

    private fun updateEvents(list: List<EventViewModel>) {
        recyclerView.adapter = EventsAdapter(
                list = list,
                eventClick = { eventId -> openEventDetailsFor(eventId) },
                addToCalendarClick = { CalendarUtils.addToCalendar(it, context!!) })
    }

    private fun openEventDetailsFor(eventId: String) {
        startActivity(EventDetailsContainerScreen.intent(context!!, paramsFor(eventId)))
    }

    private fun paramsFor(eventId: String) = EventDetailsParams(
            getEventIndex(eventId), eventList(), eventType())

    private fun getEventIndex(eventId: String): Int = eventList().indexOfFirst { it.id == eventId }

    private fun eventList(): List<EventViewModel> =
            arguments?.getParcelableArrayList(EXTRA_EVENTS) ?: emptyList()

    private fun eventType(): Event.Type =
            Event.Type.valueOf(arguments?.getString(
                    EXTRA_EVENT_TYPE, Event.Type.PRESENTATION.name).orEmpty())

    override fun injectToAppComponent(applicationComponent: ApplicationComponent) {
        //Not needed
    }
}