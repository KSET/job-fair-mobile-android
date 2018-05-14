package com.undabot.jobfair.utils

import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import android.widget.Toast
import com.undabot.jobfair.events.calendar.CalendarInfo

class CalendarUtils {

    companion object {

        fun addToCalendar(calendarInfo: CalendarInfo, context: Context) {
            val intent = Intent(Intent.ACTION_INSERT)
                    .setData(CalendarContract.Events.CONTENT_URI)
                    .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, calendarInfo.startTime.millis)
                    .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, calendarInfo.endTime.millis)
                    .putExtra(CalendarContract.Events.TITLE, calendarInfo.title)
                    .putExtra(CalendarContract.Events.EVENT_LOCATION, calendarInfo.locationName)
                    .putExtra(CalendarContract.Events.HAS_ALARM, 1)
            when (context.canHandle(intent)) {
                true -> context.startActivity(intent)
                false -> Toast.makeText(context,
                        "Unable to open Calendar application", Toast.LENGTH_SHORT).show()
            }
        }
    }
}