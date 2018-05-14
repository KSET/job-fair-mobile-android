package com.undabot.jobfair.utils

import com.undabot.jobfair.Given
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.equals
import org.joda.time.DateTime
import org.junit.Before
import org.junit.Test

class DateTimeFormatterShould {

    private lateinit var formatter: DateTimeFormatter
    private lateinit var dateTime: DateTime
    private lateinit var formatterValue: String
    private lateinit var endDateTime: DateTime

    @Before
    fun prepare() {
        formatter = DateTimeFormatter()
    }

    @Test
    fun `format dateTime AM to desired pattern`() {
        Given { dateTime = DateTime.parse("2017-05-17T09:30:00.0000000+03:00") }
        When { formatterValue = formatter.formatToDateAndTime(dateTime) }
        Then { formatterValue equals "May 17, 09:30" }
    }

    @Test
    fun `format date time PM to desired pattern`() {
        Given { dateTime = DateTime.parse("2017-05-17T14:30:00.0000000+03:00") }
        When { formatterValue = formatter.formatToDateAndTime(dateTime) }
        Then { formatterValue equals "May 17, 14:30" }
    }

    @Test
    fun `format full date time to desired pattern`() {
        Given {
            dateTime = DateTime.parse("2017-05-17T09:30:00.0000000+03:00")
            endDateTime = DateTime.parse("2017-05-17T14:30:00.0000000+03:00")
        }
        When { formatterValue = formatter.formatToFullDateTime(dateTime, endDateTime) }
        Then { formatterValue equals "May 17, 09:30 - 14:30" }
    }
}