package com.example.gitemup.common.extensions

import com.example.gitemup.config.DATETIME_ISO8601_FORMAT
import com.example.gitemup.config.DEFAULT_APP_DATETIME_FORMAT
import com.example.gitemup.config.DEFAULT_EMPTY_VALUE_PLACEHOLDER
import java.text.SimpleDateFormat
import java.util.*

fun String?.fromISO8601Time(): String {
    return this?.let {
        val df = SimpleDateFormat(DATETIME_ISO8601_FORMAT, Locale.getDefault())
        val date = df.parse(this)
        val c = Calendar.getInstance()
        c.time = date
        val hours = String.format("%02d", c.get(Calendar.HOUR_OF_DAY))
        val minutes = String.format("%02d", c.get(Calendar.MINUTE))
        "$hours:$minutes"
    } ?: kotlin.run {
        DEFAULT_EMPTY_VALUE_PLACEHOLDER
    }
}

fun Date?.fromDateToString(): String {
    val df = SimpleDateFormat(DEFAULT_APP_DATETIME_FORMAT, Locale.getDefault())
    return this?.let { df.format(it) } ?: kotlin.run {
        DEFAULT_EMPTY_VALUE_PLACEHOLDER
    }
}