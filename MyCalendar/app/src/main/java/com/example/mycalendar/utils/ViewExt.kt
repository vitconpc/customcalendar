package com.example.mycalendar.utils

fun <T> T.clone(number: Int = 1000) = MutableList(number) { this }