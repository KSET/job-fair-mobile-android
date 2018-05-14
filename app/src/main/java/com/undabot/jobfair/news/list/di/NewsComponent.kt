package com.undabot.jobfair.news.list.di

import com.undabot.jobfair.core.di.scope.PerFragment
import com.undabot.jobfair.news.list.view.NewsScreen
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = [NewsModule::class])
interface NewsComponent {

    fun inject(fragment: NewsScreen)
}