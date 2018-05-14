package com.undabot.jobfair.companies.list.di

import com.undabot.jobfair.companies.list.view.CompaniesScreen
import com.undabot.jobfair.core.di.scope.PerFragment
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = [CompaniesModule::class])
interface CompaniesComponent {

    fun inject(fragment: CompaniesScreen)
}