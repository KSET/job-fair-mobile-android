package com.undabot.jobfair.news.list.view.models

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
class NewsViewModel(
        val title: String = "",
        val description: String = "",
        val datePublished: String = "",
        val thumbnailLink: String = ""
) : Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NewsViewModel

        if (title != other.title) return false
        if (description != other.description) return false
        if (datePublished != other.datePublished) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + datePublished.hashCode()
        return result
    }
}