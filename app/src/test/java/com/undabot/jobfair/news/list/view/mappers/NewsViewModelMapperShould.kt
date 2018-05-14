package com.undabot.jobfair.news.list.view.mappers

import com.undabot.jobfair.Given
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.equals
import com.undabot.jobfair.news.entities.News
import com.undabot.jobfair.news.list.view.models.NewsViewModel
import org.joda.time.DateTime
import org.junit.Test
import org.ocpsoft.prettytime.PrettyTime

class NewsViewModelMapperShould {

    private val mapper = NewsViewModelMapper(PrettyTime().apply {
        reference = DateTime(2018, 4, 25, 10, 0).toDate()
    })

    private lateinit var result: NewsViewModel
    private lateinit var entity: News

    @Test
    fun `map title from entity to model`() {
        Given { entity = News(title = "title") }
        When { `map is requested`() }
        Then { result.title equals "title" }
    }

    @Test
    fun `map description from entity to model`() {
        Given { entity = News(description = "description") }
        When { `map is requested`() }
        Then { result.description equals "description" }
    }

    @Test
    fun `map thumbnail link from entity to viewmodel`() {
        Given { entity = News(thumbnailLink = "url") }
        When { `map is requested`() }
        Then { result.thumbnailLink equals "url" }
    }

    @Test
    fun `map date from entity to viewmodel`() {
        Given { entity = News(dateTimePublished = "Fri, 20 Apr 2018 11:18:00 +0200") }
        When { `map is requested`() }
        Then { result.datePublished equals "5 days ago" }
    }

    fun `map is requested`() {
        result = mapper.mapFrom(entity)
    }
}