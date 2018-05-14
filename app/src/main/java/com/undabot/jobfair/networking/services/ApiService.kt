package com.undabot.jobfair.networking.services

import com.undabot.jobfair.news.repository.entities.RssFeedPage
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("{language}/feed?format=rss")
    fun rssFeed(@Path("language") languageIdentifier: String): Single<RssFeedPage>
}