package com.undabot.jobfair.support.drinks.di

import com.undabot.jobfair.core.di.scope.PerActivity
import com.undabot.jobfair.support.drinks.view.DrinksActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [DrinksModule::class])
interface DrinksComponent {

    fun inject(activity: DrinksActivity)
}