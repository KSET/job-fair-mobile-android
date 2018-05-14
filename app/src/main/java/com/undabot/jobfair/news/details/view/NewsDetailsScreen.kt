package com.undabot.jobfair.news.details.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.undabot.jobfair.R
import com.undabot.jobfair.core.di.ApplicationComponent
import com.undabot.jobfair.core.view.BaseFragment
import com.undabot.jobfair.news.list.view.models.NewsViewModel
import com.undabot.jobfair.utils.ImageUtils
import kotlinx.android.synthetic.main.news_list_item_header.*
import kotlinx.android.synthetic.main.screen_details_page.*
import kotlinx.android.synthetic.main.news_list_item_header.image as headerImage
import kotlinx.android.synthetic.main.news_list_item_header.title as newsTitle

class NewsDetailsScreen : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.screen_details_page, container, false)
    }

    companion object {
        internal const val REMOVE_PADDING_PREFIX = "<body style=\"margin: 0; padding: 0\">"
        internal const val REMOVE_PADDING_SUFIX = "</body>"
        const val NEWS_ITEM = "news_item"
        fun with(newsItem: NewsViewModel) =
                NewsDetailsScreen().apply {
                    arguments = Bundle().apply {
                        putParcelable(NEWS_ITEM, newsItem)
                    }
                }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val newsItem = arguments?.getParcelable(NEWS_ITEM) as NewsViewModel
        newsTitle.text = newsItem.title
        datetime.text = newsItem.datePublished
        content.loadData(
                REMOVE_PADDING_PREFIX
                        .plus(newsItem.description)
                        .plus(REMOVE_PADDING_SUFIX), "text/html", "UTF-8")
        ImageUtils.load(
                context = context!!,
                imageUrl = newsItem.thumbnailLink,
                imageView = headerImage,
                transformOption = ImageUtils.Transform.CENTER_CROP
        )
    }

    override fun injectToAppComponent(applicationComponent: ApplicationComponent) {
    }
}
