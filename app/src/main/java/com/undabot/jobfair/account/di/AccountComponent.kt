package com.undabot.jobfair.account.di

import com.undabot.jobfair.account.view.AccountActivity
import com.undabot.jobfair.core.di.scope.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [AccountModule::class])
interface AccountComponent {

    fun inject(activity: AccountActivity)
}