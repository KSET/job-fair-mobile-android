package com.undabot.jobfair.support.assistance.di

import com.undabot.jobfair.core.di.scope.PerFragment
import com.undabot.jobfair.support.assistance.view.AssistanceActivity
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = [AssistanceModule::class])
interface AssistanceComponent {

    fun inject(activity: AssistanceActivity)
}