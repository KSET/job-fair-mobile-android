package com.undabot.jobfair.resetpassword.di

import com.undabot.jobfair.core.di.scope.PerActivity
import com.undabot.jobfair.resetpassword.view.ResetPasswordActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [ResetPasswordModule::class])
interface ResetPasswordComponent {

    fun inject(activity: ResetPasswordActivity)
}