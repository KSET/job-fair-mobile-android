package com.undabot.jobfair.news.list.di

import com.undabot.jobfair.core.di.scope.PerFragment
import com.undabot.jobfair.news.list.view.NewsContract
import com.undabot.jobfair.news.list.view.NewsCoordinator
import com.undabot.jobfair.news.list.view.NewsPresenter
import com.undabot.jobfair.news.list.view.mappers.NewsViewModelMapper
import com.undabot.jobfair.news.repository.NewsRepository
import com.undabot.jobfair.news.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import org.ocpsoft.prettytime.PrettyTime

@Module
class NewsModule {

    @Provides
    @PerFragment
    fun mapper() : NewsViewModelMapper = NewsViewModelMapper(PrettyTime())

    @Provides
    @PerFragment
    fun coordinator(coordinator: NewsCoordinator): NewsContract.Coordinator = coordinator

    @Provides
    @PerFragment
    fun presenter(presenter: NewsPresenter): NewsContract.Presenter = presenter

    @Provides
    @PerFragment
    fun repository(newsRepository: NewsRepositoryImpl): NewsRepository =
            newsRepository
}