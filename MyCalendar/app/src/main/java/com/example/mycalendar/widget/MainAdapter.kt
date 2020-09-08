package com.example.mycalendar.widget

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mycalendar.R
import com.example.mycalendar.adapter.fast.FastViewHolder
import com.example.mycalendar.utils.DateFomat
import com.example.mycalendar.utils.format
import com.example.mycalendar.utils.setEndListener
import com.example.mycalendar.widget.DayAdapter.Companion.WEEK_MODE_NUMBER
import kotlinx.android.synthetic.main.item_month.view.*
import java.util.*

class MainAdapter : RecyclerView.Adapter<FastViewHolder>() {

    var notifyAdapter: (Calendar) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FastViewHolder {
        return FastViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_month, parent, false)
        )
    }

    var midYear: Int = 0
    var weekMode: Boolean = false

    var animationOn: Boolean = false
    var WOM: Int = 0

    override fun onBindViewHolder(holder: FastViewHolder, position: Int) {
        if (weekMode) onBindWeekMode(holder, position)
        else onBindMonthMode(holder, position)
    }

    private fun onBindWeekMode(holder: FastViewHolder, position: Int) {
        val calendar = getCalendarByWeek(midYear, position - FOCUS)
        holder.itemView.tv_test.text = calendar.format(DateFomat.TYPE_4)

        val dayAdapter = DayAdapter(calendar, WEEK_MODE_NUMBER)
        holder.itemView.rv_month.run {
            adapter = dayAdapter
            layoutManager = GridLayoutManager(holder.itemView.context, WEEK_MODE_NUMBER)
        }

        holder.itemView.tv_week.setOnClickListener {
            //change to month mode
            val cal = getCalendarByWeek(midYear, position - FOCUS)
            cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY)

            WOM = cal.get(Calendar.WEEK_OF_MONTH)
            animationOn = true
            weekMode = false
            notifyAdapter(cal)
        }
    }

    private fun onBindMonthMode(holder: FastViewHolder, position: Int) {
        val year = position / MONTH_MAX
        val month = position % MONTH_MAX
        val calendar = getCalendarByMonth(year, month)

        val startDOW = calendar.get(Calendar.DAY_OF_WEEK) - 2
        holder.itemView.tv_test.text = calendar.format(DateFomat.TYPE_4)

        val dayAdapter = DayAdapter(calendar, 42, month, startDOW)
        if (animationOn) {
            animationOn = false
            dayAdapter.setAnimationMonthToWeek(notifyAdapter, getCalendarByMonth(year, month), WOM)
        }

        holder.itemView.rv_month.run {
            adapter = dayAdapter
            layoutManager = GridLayoutManager(holder.itemView.context, WEEK_MODE_NUMBER)
        }

        holder.itemView.tv_week.setOnClickListener {
            //change to week mode
            val cal = getCalendarByMonth(year, month)
            midYear = year
            weekMode = true
            animationRefeshAdapter(dayAdapter, cal)
        }
    }


    private fun animationRefeshAdapter(
        adapter: DayAdapter,
        calendar: Calendar
    ) {
        for (holder in adapter.holders) {
            val pos = holder.itemView.tag as Int
            if (pos < WEEK_MODE_NUMBER) continue
            val anim = holder.itemView.animate()
                .translationYBy(-holder.itemView.height.toFloat() * (pos / WEEK_MODE_NUMBER))
                .alpha(0f)
                .setDuration(1000)

            if (holder == adapter.holders.last()) {
                anim.setEndListener { notifyAdapter(calendar) }.start()
            } else anim.start()
        }
    }

    private fun getCalendarByMonth(year: Int, month: Int): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        return calendar
    }

    private fun getCalendarByWeek(year: Int, week: Int): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.WEEK_OF_YEAR, week)
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        return calendar
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    companion object {
        const val MONTH_MAX = 12
        const val FOCUS = Int.MAX_VALUE / 2
    }
}
