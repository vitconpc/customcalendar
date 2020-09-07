package com.example.mycalendar.utils

//import android.util.Log
//import com.rantea.animeowm.BuildConfig
//
//object Logger {
//
//    fun v(any: Any, string: String) =
//        Log.v(if (any is String) any else any::class.java.simpleName, string)
//
//    fun v(string: String) =
//        Log.v(BuildConfig.APP_NAME, string)
//
//    fun d(any: Any, string: String) =
//        Log.d(if (any is String) any else any::class.java.simpleName, string)
//
//    fun d(string: String) =
//        Log.d(BuildConfig.APP_NAME, string)
//
//    fun l(any: Any, string: String) {
//        val maxLogSize = 1000
//        for (i in 0..string.length / maxLogSize) {
//            val start = i * maxLogSize
//            var end = (i + 1) * maxLogSize
//            end = if (end > string.length) string.length else end
//            Log.v(
//                if (any is String) any else any::class.java.simpleName,
//                string.substring(start, end)
//            )
//        }
//    }
//
//    fun l(string: String) {
//        val maxLogSize = 1000
//        for (i in 0..string.length / maxLogSize) {
//            val start = i * maxLogSize
//            var end = (i + 1) * maxLogSize
//            end = if (end > string.length) string.length else end
//            Log.v(BuildConfig.APP_NAME, string.substring(start, end))
//        }
//    }
//
//    fun i(any: Any, string: String) =
//        Log.i(if (any is String) any else any::class.java.simpleName, string)
//
//    fun i(string: String) =
//        Log.i(BuildConfig.APP_NAME, string)
//
//    fun w(any: Any, string: String) =
//        Log.w(if (any is String) any else any::class.java.simpleName, string)
//
//    fun w(string: String) =
//        Log.w(BuildConfig.APP_NAME, string)
//
//    fun e(any: Any, string: String) =
//        Log.e(if (any is String) any else any::class.java.simpleName, string)
//
//    fun e(string: String) = Log.e(BuildConfig.APP_NAME, string)
//
//    fun e(any: Any, string: String, throwable: Throwable) =
//        Log.e(if (any is String) any else any::class.java.simpleName, string, throwable)
//
//    fun e(string: String, throwable: Throwable) =
//        Log.e(BuildConfig.APP_NAME, string, throwable)
//
//}