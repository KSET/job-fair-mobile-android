package com.undabot.jobfair.events.details.view

import com.undabot.jobfair.core.view.AbsPresenter
import com.undabot.jobfair.events.details.usecases.EventDetails
import com.undabot.jobfair.events.details.view.EventDetailsContract.Presenter
import com.undabot.jobfair.events.details.view.EventDetailsContract.View
import javax.inject.Inject

class EventDetailsPresenter @Inject constructor() : AbsPresenter<View>(), Presenter {

    override fun show(eventDetails: EventDetails) = onView{
        it.show(eventDetails)
    }

    override fun ratingSuccess() = onView {
        it.showRatingSuccess()
    }

    override fun ratingFailed() = onView {
        it.showRatingFailed()
    }
}