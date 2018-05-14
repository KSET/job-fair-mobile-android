package com.undabot.jobfair.events.details.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.undabot.jobfair.R
import com.undabot.jobfair.companies.view.models.CompanyViewModel
import com.undabot.jobfair.core.di.ApplicationComponent
import com.undabot.jobfair.core.view.BaseFragment
import com.undabot.jobfair.events.view.EventViewModel
import com.undabot.jobfair.utils.ImageUtils
import com.undabot.jobfair.utils.setTextOrHideIfEmpty
import kotlinx.android.synthetic.main.event_details_layout.*

class EventDetailsFragment : BaseFragment() {

    companion object {
        private val EXTRA_EVENT = "extra_event"

        fun newInstance(event: EventViewModel): EventDetailsFragment =
                EventDetailsFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(EXTRA_EVENT, event)
                    }
                }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showEventDetails(eventViewModel())
    }

    private fun eventViewModel(): EventViewModel =
            arguments?.getParcelable(EXTRA_EVENT)
                    ?: throw IllegalArgumentException("Missing presentation as argument!")

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.event_details_layout, container, false)
    }

    private fun showEventDetails(event: EventViewModel) {
        ImageUtils.load(
                context = context!!,
                imageUrl = event.companyImage,
                imageView = companyLogo
        )
        title.text = event.title
        setupCalendarButton(event)
        setupPresentersInfo(event)
        companyDetailsButton.text = getString(
                R.string.company_details_button_format, event.companyName)
        companyDetailsButton.setOnClickListener {
            (activity as EventDetailsScreen).onOpenCompanyDetailsPressed(
                    CompanyViewModel(
                            id = event.companyId,
                            name = event.companyName,
                            logoUrl = event.companyImage))
        }
        description.text = event.description
        time.text = event.fullTime
        location.text = event.location
    }

    private fun setupPresentersInfo(event: EventViewModel) {
        if (event.presentersBio.isNotEmpty() && event.presentersImage.isNotEmpty()) {
            presentersBio.text = event.presentersBio
            ImageUtils.load(
                    context = context!!,
                    imageUrl = event.presentersImage,
                    imageView = presenterImage,
                    placeholder = R.drawable.placeholder_lecturer)
        }
        presentersBio.setTextOrHideIfEmpty(event.presentersBio, presenterImage, aboutPresenter)
    }

    private fun setupCalendarButton(event: EventViewModel) {
        if (event.inPast) {
            addToCalendarButton.visibility = View.GONE
        } else {
            addToCalendarButton.setOnClickListener {
                (activity as EventDetailsScreen).onAddToCalendarPressed(event.calendarInfo)
            }
        }
    }

    override fun injectToAppComponent(applicationComponent: ApplicationComponent) {
        // Nothing to inject
    }
}