package com.example.mycalendar.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateFomat {
    val TYPE_1 = SimpleDateFormat("dd/MM/yyyy", Locale.US)
    val TYPE_2 = SimpleDateFormat("hh:mm a", Locale.US)
    val TYPE_3 = SimpleDateFormat("HH:mm:ss", Locale.US)
    val TYPE_4 = SimpleDateFormat("MMMM/yyyy", Locale.US)
}


fun Date.format(format: SimpleDateFormat): String {
    return try {
        format.format(this)
    } catch (e: ParseException) {
        ""
    }
}

fun Calendar.format(format: SimpleDateFormat): String {
    return try {
        format.format(time)
    } catch (e: ParseException) {
        ""
    }
}

fun String.parseDate(format: SimpleDateFormat): Date {
    return format.parse(this) ?: Date()
}

fun String.parseCalendar(format: SimpleDateFormat): Calendar {
    return Calendar.getInstance().apply { time = parseDate(format) }
}

fun String.formatDate(formatFrom: SimpleDateFormat, formatTo: SimpleDateFormat): String {
    return parseDate(formatFrom).format(formatTo)
}
