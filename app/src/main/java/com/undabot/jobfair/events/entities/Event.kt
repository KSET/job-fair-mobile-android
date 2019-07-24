package com.undabot.jobfair.events.entities

import com.undabot.jobfair.booths.entities.LocationInfo
import com.undabot.jobfair.companies.entities.Company
import com.undabot.jobfair.core.entities.Image
import org.joda.time.DateTime

sealed class Event {
    abstract val id: String
    abstract val title: String
    abstract val startTime: DateTime
    abstract val endTime: DateTime
    abstract val description: String
    abstract val company: Company
    abstract val location: LocationInfo

    companion object {
        val DEFAULT_DATE_TIME: DateTime = DateTime()
    }

    enum class Type {
        PRESENTATION, WORKSHOP
    }
}

data class Presentation(
    override val id: String = "",
    override val title: String = "",
    override val startTime: DateTime = DateTime(),
    override val endTime: DateTime = DateTime(),
    override val description: String = "",
    override val company: Company = Company(),
    override val location: LocationInfo = LocationInfo(),
    val presentersBio: String = "",
    val presentersImage: Image = Image()
) : Event()

data class Workshop(
        override val id: String = "",
        override val title: String = "",
        override val startTime: DateTime = DateTime(),
        override val endTime: DateTime = DateTime(),
        override val description: String = "",
        override val company: Company = Company(),
        override val location: LocationInfo = LocationInfo()
) : Event()