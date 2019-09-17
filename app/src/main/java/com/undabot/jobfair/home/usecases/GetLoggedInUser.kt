package com.undabot.jobfair.home.usecases

import com.undabot.jobfair.login.models.User
import com.undabot.jobfair.login.repository.UserRepository
import javax.inject.Inject

class GetLoggedInUser @Inject constructor(private val repository: UserRepository) {

    operator fun invoke(presenter: GetsLoggedInUser) {
        val user = repository.getLoggedInUserLocal()
        presenter.showOptionsForUser(user)
    }

    interface GetsLoggedInUser {
        fun showOptionsForUser(user: User?)
    }
}