package com.undabot.jobfair.news.list.view.mappers

import com.undabot.jobfair.news.entities.News
import com.undabot.jobfair.news.list.view.models.NewsViewModel
import org.joda.time.format.DateTimeFormat
import org.ocpsoft.prettytime.PrettyTime
import java.util.*
import javax.inject.Inject

class NewsViewModelMapper @Inject constructor(private val prettyTime: PrettyTime) {

    private val inputDatePattern = "EEE, dd MMM yyyy HH:mm:ss Z"

    fun mapFrom(news: News) = NewsViewModel(news.title,
            news.description,
            if (news.dateTimePublished.isNotEmpty()) {
                prettyTime.format(
                        DateTimeFormat.forPattern(inputDatePattern)
                                .withLocale(Locale.ENGLISH) // provided date is always in English
                                .parseDateTime(news.dateTimePublished).toDate())
            } else {
                ""
            },
            news.thumbnailLink)

}