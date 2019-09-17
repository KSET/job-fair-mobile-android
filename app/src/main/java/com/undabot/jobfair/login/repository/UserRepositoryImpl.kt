package com.undabot.jobfair.login.repository

import com.undabot.jobfair.core.schedulers.WorkerScheduler
import com.undabot.jobfair.login.models.LoginResult
import com.undabot.jobfair.login.models.User
import com.undabot.jobfair.login.repository.mapper.CurrentUserMapper
import com.undabot.jobfair.login.repository.mapper.LoginResultMapper
import com.undabot.jobfair.networking.services.ResourceApiService
import io.reactivex.Completable
import io.reactivex.Single

class UserRepositoryImpl(
    private val api: ResourceApiService,
    private val loginPreferences: LoginPreferences,
    private val loginResultMapper: LoginResultMapper,
    private val currentUserMapper: CurrentUserMapper,
    private val workerScheduler: WorkerScheduler
) : UserRepository {

    override fun login(email: String, password: String): Single<LoginResult> =
        api.login(email, password)
            .subscribeOn(workerScheduler.get())
            .map { loginResultMapper.map(it) }
            .doOnSuccess {
                loginPreferences.setToken(it.token)
                loginPreferences.setUser(it.user)
            }

    override fun resetPassword(email: String): Completable =
        api.resetPassword(email)
            .subscribeOn(workerScheduler.get())
            .map {
                when (it.success()) {
                    true -> Completable.complete()
                    else -> throw IllegalArgumentException("Request failed")
                }
            }.ignoreElement()

    override fun getLoggedInUserLocal(): User? = loginPreferences.getUser()

    override fun getLoggedInUserRemote(): Single<User> =
        api.currentUser()
            .subscribeOn(workerScheduler.get())
            .map { currentUserMapper.map(it) }
            .doOnSuccess { loginPreferences.setUser(it) }

    override fun isLoggedIn(): Boolean =
        loginPreferences.getToken().isNullOrBlank().not()
            && loginPreferences.getUser() != null

    override fun logout() {
        api.clearCache()
        loginPreferences.setToken(null)
        loginPreferences.setUser(null)
    }
}