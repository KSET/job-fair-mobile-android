package com.undabot.jobfair.networking.adapters

import com.apollographql.apollo.response.CustomTypeAdapter
import com.apollographql.apollo.response.CustomTypeValue
import org.joda.time.DateTime

class DateTimeCustomTypeAdapter : CustomTypeAdapter<DateTime> {
    override fun decode(value: CustomTypeValue<*>): DateTime =
        DateTime.parse(value.value.toString())

    override fun encode(value: DateTime): CustomTypeValue<*> =
        CustomTypeValue.GraphQLString(value.toString())
}