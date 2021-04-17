package com.fabiolee.iproperty.util

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

object TimeUtil {

    const val DATE_MMM_D_YYYY = "MMM d, yyyy"
    const val DATE_TIME_ISO_8601 = "yyyy-MM-dd'T'HH:mm:ssXXX"

    @JvmStatic
    fun formatDate(fromPattern: String, toPattern: String, value: String?): String? {
        if (value.isNullOrEmpty()) {
            return value
        } else {
            return try {
                val fromFormatter = SimpleDateFormat(fromPattern, Locale.US)
                val date = fromFormatter.parse(value)
                if (date == null) {
                    value
                } else {
                    val toFormatter = SimpleDateFormat(toPattern, Locale.US)
                    toFormatter.format(date)
                }
            } catch (e: Exception) {
                value
            }
        }
    }

}