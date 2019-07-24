package com.undabot.jobfair.events.details.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.undabot.jobfair.R
import com.undabot.jobfair.booths.view.BoothsActivity
import com.undabot.jobfair.companies.details.view.CompanyDetailsContainerScreen
import com.undabot.jobfair.companies.view.models.CompanyViewModel
import com.undabot.jobfair.core.di.ApplicationComponent
import com.undabot.jobfair.core.view.BaseFragment
import com.undabot.jobfair.events.details.di.EventDetailsModule
import com.undabot.jobfair.events.details.usecases.EventDetails
import com.undabot.jobfair.events.view.EventType
import com.undabot.jobfair.events.view.EventViewModel
import com.undabot.jobfair.utils.DateTimeFormatter
import com.undabot.jobfair.utils.ImageUtils
import com.undabot.jobfair.utils.setTextOrHideIfEmpty
import kotlinx.android.synthetic.main.event_details_layout.*
import javax.inject.Inject

class EventDetailsFragment : BaseFragment(), EventDetailsContract.View {

    companion object {
        private const val EXTRA_EVENT = "extra_event"

        fun newInstance(event: EventViewModel): EventDetailsFragment =
            EventDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_EVENT, event)
                }
            }
    }

    @Inject lateinit var coordinator: EventDetailsContract.Coordinator
    @Inject lateinit var dateTimeFormatter: DateTimeFormatter


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        coordinator.bind(this)
        coordinator.showEventRequested(eventViewModel())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.event_details_layout, container, false)
    }

    override fun showRatingFailed() {
        showGeneralErrorMessage()
        resetRatingBar()
    }

    override fun showRatingSuccess() {
        showMessage(getString(R.string.event_details_rating_success_message))
    }

    override fun show(eventDetails: EventDetails) {
        val event = eventDetails.viewModel
        eventTypeLabel.text = getString(
            if (event.type == EventType.PRESENTATION) R.string.presentation
            else R.string.workshop)
        title.text = event.title
        setupCardInfo(event)
        description.setTextOrHideIfEmpty(event.description, descriptionTitle)
        setupRatingInfo(eventDetails, event)
        bio.setTextOrHideIfEmpty(event.presentersBio, bioTitle)
        setupBoothInfo(event)
        showCompanyDetails.setOnClickListener {
            // TODO add companyViewModel to event as object
            CompanyDetailsContainerScreen.startWith(context!!, arrayListOf(
                CompanyViewModel(id = event.companyId, name = event.companyName, logoUrl = event.companyImage)
            ))
        }
    }

    private fun setupBoothInfo(event: EventViewModel) {
        if (event.location.location.isNotBlank()) {
            showBooth.setTextOrHideIfEmpty(getString(R.string.event_details_location_text_format, event.location.location))
            showBooth.setOnClickListener { BoothsActivity.startWith(context!!, event.location) }
        } else {
            showBooth.visibility = GONE
        }
    }

    private fun setupRatingInfo(eventDetails: EventDetails, event: EventViewModel) {
        if (eventDetails.ratingAvailable) {
            ratingBar.setOnRatingBarChangeListener { _, rating, fromUser ->
                if (fromUser) {
                    coordinator.eventRatedWith(event, rating.toInt())
                }
            }
            ratingBar.visibility = VISIBLE
            ratingTitle.visibility = VISIBLE
        } else {
            ratingBar.visibility = GONE
            ratingTitle.visibility = GONE
        }
    }

    private fun resetRatingBar() {
        ratingBar.rating = 0f
    }

    private fun setupCardInfo(event: EventViewModel) {
        val imageUrl: String =
            if (event.presentersImage.isNotEmpty()) event.presentersImage
            else event.companyImage

        ImageUtils.load(
            context = context!!,
            imageUrl = imageUrl,
            imageView = image,
            includeRadius = true,
            transformOption = ImageUtils.Transform.FIT_CENTER,
            placeholder = R.drawable.placeholder_lecturer)

        date.text = dateTimeFormatter.formatToDate(event.calendarInfo.startTime)
        time.text = getString(R.string.event_details_time_text_format,
            dateTimeFormatter.formatToTime(event.calendarInfo.startTime))
    }

    private fun eventViewModel(): EventViewModel =
        arguments?.getParcelable(EXTRA_EVENT)
            ?: throw IllegalArgumentException("Missing presentation as argument!")

    override fun injectToAppComponent(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(EventDetailsModule()).inject(this)
    }
}