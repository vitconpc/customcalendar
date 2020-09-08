package com.example.mycalendar.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.example.mycalendar.R
import com.example.mycalendar.widget.MainAdapter.Companion.FOCUS
import com.example.mycalendar.widget.MainAdapter.Companion.MONTH_MAX
import kotlinx.android.synthetic.main.layout_calendar.view.*
import java.util.*

class CalendarView : FrameLayout {

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
        inflate(context, R.layout.layout_calendar, this)
        val adapter = MainAdapter()
        adapter.notifRefreshAdapter = {
            if (adapter.weekMode) {
                val week = it.get(Calendar.WEEK_OF_YEAR)
                vp_calendar.setCurrentItem(FOCUS + week, false)
            } else {
                val year = it.get(Calendar.YEAR)
                val month = it.get(Calendar.MONTH)
                vp_calendar.setCurrentItem(year * MONTH_MAX + month, false)
                adapter.notifyDataSetChanged()
            }
        }
        vp_calendar.adapter = adapter
        vp_calendar.setCurrentItem(2020 * MONTH_MAX + 5, false)
    }
}