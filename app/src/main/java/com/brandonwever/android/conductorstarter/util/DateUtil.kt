package com.brandonwever.android.conductorstarter.util

import org.threeten.bp.LocalDate
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

class DateUtil {
    fun toISO(dateTime: ZonedDateTime): String {
        return dateTime.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)
    }

    fun toDate(localDate: LocalDate): String {
        return localDate.format(DateTimeFormatter.ISO_LOCAL_DATE)
    }
}