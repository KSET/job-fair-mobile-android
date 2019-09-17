package com.undabot.jobfair.core.di

import com.undabot.jobfair.App
import com.undabot.jobfair.about.di.AboutComponent
import com.undabot.jobfair.about.di.AboutModule
import com.undabot.jobfair.account.di.AccountComponent
import com.undabot.jobfair.account.di.AccountModule
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
import com.undabot.jobfair.home.di.HomeComponent
import com.undabot.jobfair.home.di.HomeModule
import com.undabot.jobfair.login.di.LoginComponent
import com.undabot.jobfair.login.di.LoginModule
import com.undabot.jobfair.networking.di.NetworkingModule
import com.undabot.jobfair.networking.services.ApiService
import com.undabot.jobfair.news.list.di.NewsComponent
import com.undabot.jobfair.news.list.di.NewsModule
import com.undabot.jobfair.resetpassword.di.ResetPasswordComponent
import com.undabot.jobfair.resetpassword.di.ResetPasswordModule
import com.undabot.jobfair.review.di.StudentReviewComponent
import com.undabot.jobfair.review.di.StudentReviewModule
import com.undabot.jobfair.scanqr.di.ScanQrComponent
import com.undabot.jobfair.scanqr.di.ScanQrModule
import com.undabot.jobfair.splash.di.SplashComponent
import com.undabot.jobfair.splash.di.SplashModule
import com.undabot.jobfair.support.assistance.di.AssistanceComponent
import com.undabot.jobfair.support.assistance.di.AssistanceModule
import com.undabot.jobfair.support.drinks.di.DrinksComponent
import com.undabot.jobfair.support.drinks.di.DrinksModule
import com.undabot.jobfair.support.submitcv.di.SubmitCvComponent
import com.undabot.jobfair.support.submitcv.di.SubmitCvModule
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
    fun plus(module: LoginModule): LoginComponent
    fun plus(module: ResetPasswordModule): ResetPasswordComponent
    fun plus(module: SplashModule): SplashComponent
    fun plus(module: HomeModule): HomeComponent
    fun plus(module: AssistanceModule): AssistanceComponent
    fun plus(module: DrinksModule): DrinksComponent
    fun plus(module: ScanQrModule): ScanQrComponent
    fun plus(module: StudentReviewModule): StudentReviewComponent
    fun plus(module: SubmitCvModule): SubmitCvComponent
    fun plus(module: AccountModule): AccountComponent
}