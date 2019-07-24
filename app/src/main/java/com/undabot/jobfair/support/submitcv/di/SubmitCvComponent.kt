package com.undabot.jobfair.support.submitcv.di

import com.undabot.jobfair.core.di.scope.PerActivity
import com.undabot.jobfair.support.submitcv.view.SubmitCvActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [SubmitCvModule::class])
interface SubmitCvComponent {

    fun inject(activity: SubmitCvActivity)
}