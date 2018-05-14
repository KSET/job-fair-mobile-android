package com.undabot.jobfair

import android.support.multidex.MultiDexApplication
import com.undabot.jobfair.core.di.ApplicationComponent
import com.undabot.jobfair.core.di.ApplicationModule
import com.undabot.jobfair.core.di.DaggerApplicationComponent
import com.undabot.jobfair.networking.di.NetworkingModule
import net.danlew.android.joda.JodaTimeAndroid

class App : MultiDexApplication() {

    companion object {
        lateinit var instance: App
            private set
    }

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        instance = this
        super.onCreate()
        initDaggerComponent()
        JodaTimeAndroid.init(this)
    }

    private fun initDaggerComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .networkingModule(NetworkingModule())
                .build()
        applicationComponent.inject(this)
    }
}