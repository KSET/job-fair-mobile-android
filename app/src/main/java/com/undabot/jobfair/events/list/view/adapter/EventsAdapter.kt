package com.undabot.jobfair.events.list.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.undabot.jobfair.R
import com.undabot.jobfair.events.calendar.CalendarInfo
import com.undabot.jobfair.events.view.EventViewModel
import com.undabot.jobfair.utils.ImageUtils

class EventsAdapter(
        private val list: List<EventViewModel>,
        private val eventClick: (presentationId: String) -> Unit = {},
        private val addToCalendarClick: (CalendarInfo) -> Unit = {}
) : RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.event_list_item, parent, false),
            eventClick, addToCalendarClick)

    class ViewHolder(
            itemView: View,
            private val eventClick: (presentationId: String) -> Unit = {},
            private val addToCalendarClick: (CalendarInfo) -> Unit = {}
    ) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.image)
        private val title: TextView = itemView.findViewById(R.id.title)
        private val startTime: TextView = itemView.findViewById(R.id.date_time)
        private val addToCalendar: ImageButton = itemView.findViewById(R.id.addToCalendarButton)
        private val inProgressLabel: TextView = itemView.findViewById(R.id.in_progress_label)

        fun bind(viewModel: EventViewModel) {
            title.text = viewModel.title
            startTime.text = viewModel.startTime
            ImageUtils.load(
                    context = itemView.context,
                    imageUrl = listImageFrom(viewModel),
                    imageView = image,
                    transformOption = ImageUtils.Transform.FIT_CENTER
            )
            itemView.setOnClickListener { eventClick.invoke(viewModel.id) }
            addToCalendar.setOnClickListener { addToCalendarClick.invoke(viewModel.calendarInfo) }

            when (viewModel.currentlyInProgress) {
                true -> inProgressLabel.visibility = View.VISIBLE
                false -> inProgressLabel.visibility = View.GONE
            }

            when (viewModel.inPast) {
                true -> addToCalendar.visibility = View.GONE
                false -> addToCalendar.visibility = View.VISIBLE
            }
        }

        private fun listImageFrom(viewModel: EventViewModel) =
                if (viewModel.presentersImage.isNotEmpty()) viewModel.presentersImage
                else viewModel.companyImage
    }
}