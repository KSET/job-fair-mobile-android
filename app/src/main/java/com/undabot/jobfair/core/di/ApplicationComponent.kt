package com.undabot.jobfair.core.di

import com.undabot.jobfair.App
import com.undabot.jobfair.about.di.AboutComponent
import com.undabot.jobfair.about.di.AboutModule
import com.undabot.jobfair.booths.di.BoothsComponent
import com.undabot.jobfair.booths.di.BoothsModule
import com.undabot.jobfair.companies.details.di.CompaniesDetailComponent
import com.undabot.jobfair.companies.details.di.CompanyDetailsModule
import com.undabot.jobfair.companies.list.di.CompaniesComponent
import com.undabot.jobfair.companies.list.di.CompaniesModule
import com.undabot.jobfair.core.di.scope.PerApplication
import com.undabot.jobfair.events.details.di.EventDetailsComponent
import com.undabot.jobfair.events.details.di.EventDetailsModule
import com.undabot.jobfair.events.list.di.EventsComponent
import com.undabot.jobfair.events.list.di.EventsModule
import com.undabot.jobfair.networking.di.NetworkingModule
import com.undabot.jobfair.networking.services.ApiService
import com.undabot.jobfair.news.list.di.NewsComponent
import com.undabot.jobfair.news.list.di.NewsModule
import dagger.Component
import javax.inject.Named

@PerApplication
@Component(
        modules = [
            ApplicationModule::class,
            NetworkingModule::class
        ]
)

interface ApplicationComponent {

    fun inject(app: App)

    @Named("language")
    fun languageId(): String
    fun apiService(): ApiService

    fun plus(module: NewsModule): NewsComponent
    fun plus(module: CompaniesModule): CompaniesComponent
    fun plus(module: CompanyDetailsModule): CompaniesDetailComponent
    fun plus(module: EventsModule): EventsComponent
    fun plus(module: EventDetailsModule): EventDetailsComponent
    fun plus(module: BoothsModule): BoothsComponent
    fun plus(module: AboutModule): AboutComponent
}