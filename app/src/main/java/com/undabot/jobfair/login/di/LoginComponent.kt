package com.undabot.jobfair.login.di

import com.undabot.jobfair.core.di.scope.PerActivity
import com.undabot.jobfair.login.view.LoginActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [LoginModule::class])
interface LoginComponent {

    fun inject(activity: LoginActivity)
}