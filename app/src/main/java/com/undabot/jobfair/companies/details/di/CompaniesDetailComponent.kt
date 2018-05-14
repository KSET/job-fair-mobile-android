package com.undabot.jobfair.companies.details.di

import com.undabot.jobfair.companies.details.view.CompanyDetailsScreen
import com.undabot.jobfair.core.di.scope.PerFragment
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = [CompanyDetailsModule::class])
interface CompaniesDetailComponent {

    fun inject(fragment: CompanyDetailsScreen)
}