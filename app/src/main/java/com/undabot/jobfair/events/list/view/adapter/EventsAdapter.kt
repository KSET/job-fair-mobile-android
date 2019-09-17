package com.undabot.jobfair.events.list.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.undabot.jobfair.R
import com.undabot.jobfair.events.calendar.CalendarInfo
import com.undabot.jobfair.events.list.view.TimelineDots
import com.undabot.jobfair.events.view.EventViewModel
import com.undabot.jobfair.utils.ImageUtils

class EventsAdapter(
    private val list: List<EventViewModel>,
    private val eventClick: (presentationId: String) -> Unit = {},
    private val addToCalendarClick: (CalendarInfo) -> Unit = {}
) : RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], position == 0, position == list.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.event_list_item, parent, false),
        eventClick, addToCalendarClick)

    class ViewHolder(
        itemView: View,
        private val eventClick: (presentationId: String) -> Unit = {},
        private val addToCalendarClick: (CalendarInfo) -> Unit = {}
    ) : RecyclerView.ViewHolder(itemView) {

        private val inProgressColor = ContextCompat.getColor(itemView.context, R.color.inProgressStrokeColor)
        private val defaultColor = ContextCompat.getColor(itemView.context, R.color.defaultStrokeColor)

        private val timeline: TimelineDots = itemView.findViewById(R.id.timeline)
        private val card: MaterialCardView = itemView.findViewById(R.id.card)
        private val image: ImageView = itemView.findViewById(R.id.image)
        private val title: TextView = itemView.findViewById(R.id.title)
        private val detailText1: TextView = itemView.findViewById(R.id.detailText1)
        private val detailText2: TextView = itemView.findViewById(R.id.detailText2)
        private val addToCalendar: ImageButton = itemView.findViewById(R.id.addToCalendarButton)

        fun bind(viewModel: EventViewModel, isFirst: Boolean, isLast: Boolean) {
            timeline.timeIndicator = TimelineDots.TimeIndicator.from(viewModel.startTime, viewModel.endTime)
            timeline.hasTopDots = !isFirst
            timeline.hasBottomDots = !isLast

            title.text = viewModel.title
            detailText1.text = viewModel.location.location
            detailText2.text = viewModel.startTimeFormatted
            ImageUtils.load(
                context = itemView.context,
                imageUrl = listImageFrom(viewModel),
                imageView = image,
                includeRadius = true,
                transformOption = ImageUtils.Transform.FIT_CENTER
            )
            itemView.setOnClickListener { eventClick.invoke(viewModel.id) }
            addToCalendar.setOnClickListener { addToCalendarClick.invoke(viewModel.calendarInfo) }

            if (viewModel.currentlyInProgress) {
                card.strokeColor = inProgressColor
            } else {
                card.strokeColor = defaultColor
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