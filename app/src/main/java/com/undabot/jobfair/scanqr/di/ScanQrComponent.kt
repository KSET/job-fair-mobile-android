package com.undabot.jobfair.scanqr.di

import com.undabot.jobfair.core.di.scope.PerActivity
import com.undabot.jobfair.scanqr.view.ScanQrActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [ScanQrModule::class])
interface ScanQrComponent {

    fun inject(activity: ScanQrActivity)
}