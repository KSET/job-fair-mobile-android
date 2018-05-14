package com.undabot.jobfair.news.list.view

import com.undabot.jobfair.core.view.AbsPresenter
import com.undabot.jobfair.news.entities.News
import com.undabot.jobfair.news.list.view.mappers.NewsViewModelMapper
import javax.inject.Inject

class NewsPresenter @Inject
constructor(private val mapper: NewsViewModelMapper) :
        NewsContract.Presenter, AbsPresenter<NewsContract.View>() {

    override fun displayLoadingFeed() = onView { it.displayLoading() }

    override fun displayError(message: String) =
            onView {
                it.displayReady()
                it.displayError(message)
            }

    override fun displayFeed(items: List<News>) =
            onView {
                it.displayReady()
                it.displayFeed(items.map { mapper.mapFrom(it) })
            }
}