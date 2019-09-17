package com.undabot.jobfair.login.repository

import com.undabot.jobfair.login.models.LoginResult
import com.undabot.jobfair.login.models.User
import io.reactivex.Completable
import io.reactivex.Single

interface UserRepository {
    fun login(email: String, password: String): Single<LoginResult>
    fun resetPassword(email: String): Completable
    fun getLoggedInUserLocal(): User?
    fun getLoggedInUserRemote(): Single<User>
    fun isLoggedIn(): Boolean
    fun logout()
}