package com.rantea.animeowm.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.view.get
import com.example.mycalendar.R

import com.rantea.animeowm.widget.MonthWheelAdapter.Companion.FEB
import com.rantea.animeowm.widget.MonthWheelAdapter.Companion.MONTH_MAX
import kotlinx.android.synthetic.main.layout_calendar.view.*
import java.text.SimpleDateFormat
import java.util.*

class CalendarView : FrameLayout {

    lateinit var calendar: Calendar

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
    }

    constructor(context: Context) : super(context) {
        initView()
    }

    private fun initView() {
        calendar = Calendar.getInstance()
        inflate(context, R.layout.layout_calendar, this)
        vp_calendar.adapter = MonthWheelAdapter {
//            Logger.e(it.time.format(SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH)))
            (vp_calendar.adapter as MonthWheelAdapter).let { adapter ->
                adapter.midYear = it.get(Calendar.YEAR)
                adapter.weekMode = true
                vp_calendar.setCurrentItem(adapter.focus + it.get(Calendar.WEEK_OF_YEAR), false)

            }
//            vp_calendar.setCurrentItem(it.get(Calendar.WEEK_OF_YEAR), false)
        }
        vp_calendar.setCurrentItem(2020 * MONTH_MAX + 5, false)
    }
}