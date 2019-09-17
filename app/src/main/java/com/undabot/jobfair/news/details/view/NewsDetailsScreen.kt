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
import kotlinx.android.synthetic.main.screen_details_page.*

class NewsDetailsScreen : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.screen_details_page, container, false)
    }

    companion object {
        internal const val HTML_PREFIX = """<head>
<head><style>@font-face {font-family: 'montserrat';src: url('file:///android_asset/fonts/montserrat_regular.ttf');}body {font-family: 'montserrat';margin: 0; padding: 0; color:"#333333"; line-height:1.6em}</style></head><body>"""
        internal const val HTML_SUFIX = "</body></head>"
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
        title.text = newsItem.title
        datetime.text = newsItem.datePublished
        content.settings.defaultFontSize = 14
        content.loadDataWithBaseURL(null,
            HTML_PREFIX
                .plus(newsItem.description)
                .plus(HTML_SUFIX), "text/html", "UTF-8", null)
        ImageUtils.load(
            context = context!!,
            imageUrl = newsItem.thumbnailLink,
            imageView = image,
            includeRadius = true,
            transformOption = ImageUtils.Transform.CENTER_CROP
        )
    }

    override fun injectToAppComponent(applicationComponent: ApplicationComponent) {
    }
}
