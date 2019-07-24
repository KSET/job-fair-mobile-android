package com.undabot.jobfair.core.di

import android.content.Context
import android.content.res.Resources
import com.google.gson.Gson
import com.undabot.jobfair.App
import com.undabot.jobfair.core.di.scope.PerApplication
import com.undabot.jobfair.core.schedulers.MainScheduler
import com.undabot.jobfair.core.schedulers.WorkerScheduler
import com.undabot.jobfair.login.repository.LoginPreferences
import com.undabot.jobfair.login.repository.UserRepository
import com.undabot.jobfair.login.repository.UserRepositoryImpl
import com.undabot.jobfair.login.repository.mapper.CurrentUserMapper
import com.undabot.jobfair.login.repository.mapper.LoginResultMapper
import com.undabot.jobfair.networking.services.ResourceApiService
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

@Module
class ApplicationModule(private val app: App) {

    @Provides
    @PerApplication
    fun providesApplication(): App = app

    @Provides
    @PerApplication
    fun provideApplicationContext(): Context = app

    @Provides
    @PerApplication
    fun provideMainScheduler() = MainScheduler(AndroidSchedulers.mainThread())

    @Provides
    @PerApplication
    fun providePostExecutionThread() = WorkerScheduler(Schedulers.io())

    @Provides
    @PerApplication
    fun resources(): Resources = app.resources

    @Provides
    @PerApplication
    fun userRepository(
        api: ResourceApiService,
        loginPreferences: LoginPreferences,
        loginResultMapper: LoginResultMapper,
        currentUserMapper: CurrentUserMapper,
        workerScheduler: WorkerScheduler
    ): UserRepository = UserRepositoryImpl(api, loginPreferences, loginResultMapper,currentUserMapper, workerScheduler)

    @Provides
    @PerApplication
    fun loginPreferences(context: Context, gson: Gson): LoginPreferences = LoginPreferences(context, gson)

    @Provides
    @PerApplication
    @Named("language")
    fun languageId() = "en"

    @Provides
    @PerApplication
    fun gson(): Gson = Gson()
}