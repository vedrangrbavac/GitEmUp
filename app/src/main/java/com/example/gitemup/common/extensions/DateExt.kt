package com.example.gitemup.common.extensions

import com.example.gitemup.config.DEFAULT_APP_DATETIME_FORMAT
import com.example.gitemup.config.DEFAULT_EMPTY_VALUE_PLACEHOLDER
import java.text.SimpleDateFormat
import java.util.*


fun Date?.fromDateToString(): String {
    val df = SimpleDateFormat(DEFAULT_APP_DATETIME_FORMAT, Locale.getDefault())
    return this?.let { df.format(it) } ?: kotlin.run {
        DEFAULT_EMPTY_VALUE_PLACEHOLDER
    }
}