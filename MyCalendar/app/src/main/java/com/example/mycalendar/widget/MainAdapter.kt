package com.rantea.animeowm.widget

import androidx.recyclerview.widget.GridLayoutManager
import com.example.mycalendar.R
import com.example.mycalendar.utils.DateFomat
import com.example.mycalendar.utils.Logger
import com.example.mycalendar.utils.format
import com.example.mycalendar.utils.setEndListener
import com.rantea.animeowm.adapter.fast.FastAdapter
import com.rantea.animeowm.adapter.fast.FastViewHolder
import com.rantea.animeowm.adapter.fast.OnBindView

import com.rantea.animeowm.widget.DayAdapter.Companion.WEEK_MODE_NUMBER
import kotlinx.android.synthetic.main.item_month.view.*
import java.util.*

class MainAdapter(val weekModeRefeshAdapter: (Calendar) -> Unit) : FastAdapter<Any>(
    mutableListOf(), object : OnBindView() {
        override fun onBindView(holder: FastViewHolder, position: Int) {}
    }, R.layout.item_month
) {

    var midYear: Int = 0
    var weekMode: Boolean = false

    override fun onBindViewHolder(holder: FastViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        if (weekMode) {
            holder.itemView.rv_month.run {
                val calendar = getCalendarByWeek(midYear, position - FOCUS)
                Logger.e("4:" + calendar.format(DateFomat.TYPE_1))
                holder.itemView.tv_test.text = calendar.format(DateFomat.TYPE_4)

                adapter = DayAdapter(calendar, WEEK_MODE_NUMBER)
                layoutManager = GridLayoutManager(holder.itemView.context, 7)
            }
        } else {
            val year = position / MONTH_MAX
            val month = position % MONTH_MAX
            val calendar = getCalendarByMonth(year, month)
            val startDOW = calendar.get(Calendar.DAY_OF_WEEK) - 2

            holder.itemView.tv_test.text = calendar.format(DateFomat.TYPE_4)

            holder.itemView.rv_month.run {
                adapter = DayAdapter(calendar, 42, month, startDOW)
                layoutManager = GridLayoutManager(holder.itemView.context, 7)
            }

            holder.itemView.tv_week.setOnClickListener {
                val calendar1 = getCalendarByMonth(year, month)
                Logger.e("1:" + calendar1.format(DateFomat.TYPE_1))
                calendar1.set(Calendar.DAY_OF_WEEK, 1)
                midYear = year
                weekMode = true
                Logger.e("2:" + calendar1.format(DateFomat.TYPE_1))

                animationRefeshAdapter(holder.itemView.rv_month.adapter as DayAdapter, calendar1)
            }
        }
    }

    private fun animationRefeshAdapter(
        adapter: DayAdapter,
        calendar: Calendar
    ) {
        for (holder in adapter.holders) {
            val pos = holder.itemView.tag as Int
            if (pos < 7) continue
            val anim = holder.itemView.animate()
                .translationYBy(-holder.itemView.height.toFloat() * (pos / 7))
                .alpha(0f)
                .setDuration(1000)

            if (holder == adapter.holders.last()) {
                anim.setEndListener { weekModeRefeshAdapter(calendar) }.start()
            } else anim.start()
        }
    }

    fun getCalendarByMonth(year: Int, month: Int): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        return calendar
    }

    fun getCalendarByWeek(year: Int, week: Int): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.WEEK_OF_YEAR, week)
        calendar.set(Calendar.DAY_OF_WEEK, 2)
        return calendar
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    companion object {
        const val MONTH_MAX = 12
        const val FOCUS = Int.MAX_VALUE / 2
    }
}
