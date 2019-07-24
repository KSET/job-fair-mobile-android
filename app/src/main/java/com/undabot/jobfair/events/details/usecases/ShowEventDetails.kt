package com.undabot.jobfair.events.details.usecases

import com.undabot.jobfair.events.view.EventViewModel
import com.undabot.jobfair.login.models.UserType
import com.undabot.jobfair.login.repository.UserRepository
import javax.inject.Inject

class ShowEventDetails @Inject constructor(
    private val userRepository: UserRepository
) {

    operator fun invoke(eventViewModel: EventViewModel,
                        presenter: ShowsEventDetails) {
        val ratingAvailable = when (userRepository.getLoggedInUserLocal()?.type) {
            UserType.STUDENT -> true
            else -> false
        }
        presenter.show(EventDetails(
            eventViewModel,
            ratingAvailable
        ))
    }

    interface ShowsEventDetails {
        fun show(eventDetails: EventDetails)
    }
}

data class EventDetails(
    val viewModel: EventViewModel,
    val ratingAvailable: Boolean
)