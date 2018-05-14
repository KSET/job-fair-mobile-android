package com.undabot.jobfair.networking.adapters

import com.apollographql.apollo.CustomTypeAdapter
import org.joda.time.DateTime

class DateTimeCustomTypeAdapter : CustomTypeAdapter<DateTime> {

    override fun decode(value: String): DateTime = DateTime.parse(value)

    override fun encode(value: DateTime): String = value.toString()
}