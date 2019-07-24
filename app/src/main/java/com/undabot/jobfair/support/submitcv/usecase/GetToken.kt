package com.undabot.jobfair.support.submitcv.usecase

import com.undabot.jobfair.login.repository.LoginPreferences
import javax.inject.Inject

class GetToken @Inject constructor(private val loginPreferences: LoginPreferences) {

    operator fun invoke(presenter: GetsToken) {
        val token = loginPreferences.getToken()
        if (token != null) {
            presenter.tokenRequestSuccess(token)
        } else {
            presenter.tokenRequestError()
        }
    }

    interface GetsToken {
        fun tokenRequestSuccess(token: String)
        fun tokenRequestError()
    }
}