package com.undabot.jobfair.news.repository

import com.undabot.jobfair.news.entities.News

interface NewsRepository {
    fun fetchFeedItems(success: (List<News>) -> Unit, error: (String) -> Unit)
}