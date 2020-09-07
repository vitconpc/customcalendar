package com.example.mycalendar.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateFomat {
    val TYPE_1 = SimpleDateFormat("MM/dd/yyyy", Locale.US)
    val TYPE_2 = SimpleDateFormat("hh:mm a", Locale.US)
    val TYPE_3 = SimpleDateFormat("HH:mm:ss", Locale.US)
    //    val TYPE_3 = SimpleDateFormat("MMMM d, yyyy (EEE)", Locale.US)
//    val TYPE_JP_3 = SimpleDateFormat("yyyy年MM月dd日(EEE)", Locale.US)
//    val TYPE_4 = SimpleDateFormat("yyyy-MM-dd", Locale.US)
//    val TYPE_5 = SimpleDateFormat("HH:mm", Locale.US)
    //    val TYPE_5 = SimpleDateFormat("MMMM d, yyyy", Locale.US)
    val TYPE_JP_5 = SimpleDateFormat("yyyy年MM月dd日", Locale.US)
    val TYPE_JP_6 = SimpleDateFormat("yyyy年MM月", Locale.US)
    val TYPE_7 = SimpleDateFormat("yyyy/MM/dd", Locale.US)
//    val TYPE_JP_6 = SimpleDateFormat("MM月dd日", Locale.US)
//    val TYPE_6 = SimpleDateFormat("MMM d", Locale.US)
//    val TYPE_8 = SimpleDateFormat("yyyy/M/d", Locale.US)
//    val TYPE_9 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ", Locale.US)
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
