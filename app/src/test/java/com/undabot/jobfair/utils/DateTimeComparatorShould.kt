package com.undabot.jobfair.utils

import com.undabot.jobfair.Given
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.equals
import org.joda.time.DateTime
import org.junit.Before
import org.junit.Test

class DateTimeComparatorShould {

    private lateinit var comparator: DateTimeComparator
    private lateinit var startDateTime: DateTime
    private lateinit var endDateTime: DateTime
    private var result: Boolean = false

    @Before
    fun prepare() {
        comparator = DateTimeComparator()
    }

    @Test
    fun `return true when current time is between start and end time`() {
        Given {
            startDateTime = DateTime.now().minusMinutes(20)
            endDateTime = DateTime.now().plusMinutes(10)
        }
        When { `is now is called`() }
        Then { result equals true }
    }

    @Test
    fun `return false when current time is not between start and end time`() {
        Given {
            startDateTime = DateTime.now().minusMinutes(20)
            endDateTime = DateTime.now().minusMinutes(10)
        }
        When { `is now is called`() }
        Then { result equals false }
    }

    @Test
    fun `return true when given time is in past and is in past is requested`() {
        Given { startDateTime = DateTime.now().minusMinutes(3) }
        When { `is in past called`() }
        Then { result equals true }
    }

    @Test
    fun `return false when given time is in the future and is in past is requested`() {
        Given { startDateTime = DateTime.now().plusMinutes(3) }
        When { `is in past called`() }
        Then { result equals false }
    }

    private fun `is in past called`() {
        result = comparator.isInPast(startDateTime)
    }

    private fun `is now is called`() {
        result = comparator.isNow(startDateTime, endDateTime)
    }
}