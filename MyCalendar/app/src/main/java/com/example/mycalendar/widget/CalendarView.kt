package com.rantea.animeowm.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.example.mycalendar.R
import com.example.mycalendar.utils.DateFomat
import com.example.mycalendar.utils.Logger
import com.example.mycalendar.utils.format
import com.rantea.animeowm.widget.MainAdapter.Companion.FOCUS
import com.rantea.animeowm.widget.MainAdapter.Companion.MONTH_MAX
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
        vp_calendar.adapter = MainAdapter {
            Logger.e("3:" + it.format(DateFomat.TYPE_1))
            vp_calendar.setCurrentItem(FOCUS + it.get(Calendar.WEEK_OF_YEAR), false)
        }
        vp_calendar.setCurrentItem(2020 * MONTH_MAX, false)
    }
}