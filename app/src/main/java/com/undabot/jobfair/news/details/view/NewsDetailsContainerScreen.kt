package com.undabot.jobfair.news.details.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.widget.Toolbar
import com.undabot.jobfair.R
import com.undabot.jobfair.core.di.ApplicationComponent
import com.undabot.jobfair.core.view.BaseActivity
import com.undabot.jobfair.news.list.view.models.NewsViewModel
import kotlinx.android.synthetic.main.navigatable_pager.view.*
import kotlinx.android.synthetic.main.screen_news_details.*

class NewsDetailsContainerScreen : BaseActivity() {

    companion object {
        private const val NEWS_ITEMS = "NEWS_ITEMS"
        const val CHOSEN_ITEM = "CHOSEN_ITEM"

        fun startWith(context: Context, newsItem: ArrayList<NewsViewModel>, position: Int) {
            context.startActivity(Intent(context, NewsDetailsContainerScreen::class.java)
                    .apply {
                        putExtra(NEWS_ITEMS, newsItem)
                        putExtra(CHOSEN_ITEM, position)
                    })
        }
    }

    private lateinit var toolbar: Toolbar

    override fun injectToAppComponent(applicationComponent: ApplicationComponent) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_news_details)
        toolbar = findViewById(R.id.toolbar)
        toolbar.title = getString(R.string.news)
        toolbar.setNavigationOnClickListener { super.onBackPressed() }
        val list = intent.getParcelableArrayListExtra<NewsViewModel>(NEWS_ITEMS)
        val position = intent.getIntExtra(CHOSEN_ITEM, 0)
        detailPager.pager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getCount(): Int {
                return list.size
            }

            override fun getItem(position: Int): Fragment {
                return NewsDetailsScreen.with(list[position])
            }
        }
        detailPager.pager.setCurrentItem(position, false)
    }
}
