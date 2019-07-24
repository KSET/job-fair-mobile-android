package com.undabot.jobfair.news.list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.undabot.jobfair.R
import com.undabot.jobfair.core.di.ApplicationComponent
import com.undabot.jobfair.core.view.BaseFragment
import com.undabot.jobfair.news.details.view.NewsDetailsContainerScreen
import com.undabot.jobfair.news.list.di.NewsModule
import com.undabot.jobfair.news.list.view.adapters.NewsListAdapter
import com.undabot.jobfair.news.list.view.models.NewsViewModel
import kotlinx.android.synthetic.main.screen_news.*
import javax.inject.Inject
import kotlinx.android.synthetic.main.screen_news.pull_to_refresh as pullToRefresh

class NewsScreen : BaseFragment(), NewsContract.View {

    @Inject
    lateinit var coordinator: NewsContract.Coordinator

    private val newsListAdapter = NewsListAdapter(mutableListOf()) { list, position ->
        NewsDetailsContainerScreen.startWith(context!!, ArrayList(list), position)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.screen_news, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        pullToRefresh.setOnRefreshListener {
            coordinator.fetchFeed()
        }
        list.layoutManager = LinearLayoutManager(activity)
        list.adapter = newsListAdapter
        coordinator.bind(this)
        coordinator.fetchFeed()
    }

    override fun displayFeed(items: List<NewsViewModel>) {
        newsListAdapter.updateItems(items)
    }

    override fun displayError(errorMessage: String) {
        showMessage(errorMessage)
    }

    override fun displayLoading() {
        loading.visibility = View.VISIBLE
    }

    override fun displayReady() {
        loading.visibility = View.GONE
        pullToRefresh.isRefreshing = false
    }

    override fun injectToAppComponent(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(NewsModule()).inject(this)
    }

    override fun onDestroy() {
        coordinator.unbind(this)
        super.onDestroy()
    }
}