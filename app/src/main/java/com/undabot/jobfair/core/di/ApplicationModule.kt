package com.undabot.jobfair.core.di

import android.content.Context
import android.content.res.Resources
import com.undabot.jobfair.App
import com.undabot.jobfair.core.di.scope.PerApplication
import com.undabot.jobfair.core.schedulers.MainScheduler
import com.undabot.jobfair.core.schedulers.WorkerScheduler
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
    @Named("language")
    fun languageId() = "en"
}