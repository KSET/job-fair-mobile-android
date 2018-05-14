package com.undabot.jobfair.news.entities

import java.io.Serializable

data class News(
    val title: String = "",
    val dateTimePublished: String = "",
    val thumbnailLink: String = "",
    val description: String = ""
) : Serializable