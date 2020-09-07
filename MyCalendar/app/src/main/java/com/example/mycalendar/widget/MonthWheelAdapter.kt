package com.rantea.animeowm.widget

import androidx.recyclerview.widget.GridLayoutManager
import com.example.mycalendar.R
import com.example.mycalendar.utils.format
import com.rantea.animeowm.adapter.fast.FastAdapter
import com.rantea.animeowm.adapter.fast.FastViewHolder
import com.rantea.animeowm.adapter.fast.OnBindView
import kotlinx.android.synthetic.main.item_month.view.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.Logger

class MonthWheelAdapter(val onClickWeekMode: (Calendar) -> Unit) : FastAdapter<Any>(
    mutableListOf(), object : OnBindView() {
        override fun onBindView(holder: FastViewHolder, position: Int) {}
    }, R.layout.item_month
) {

    var midYear: Int = 0
    var weekMode: Boolean = false
    val focus = Int.MAX_VALUE / 2

    override fun onBindViewHolder(holder: FastViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        if (weekMode) {
            holder.itemView.rv_month.run {
                val calendar = getCalendar(midYear, position - focus)
                val startDOW = calendar.get(Calendar.DAY_OF_WEEK) - 2

                adapter = dayAdapter2(calendar, 7)
                layoutManager = GridLayoutManager(holder.itemView.context, 7)
            }
        } else {
            val year = position / MONTH_MAX
            val month = MONTHS[position % MONTH_MAX]
            holder.itemView.tv_test.text = (year.toString() + "/" + month.name)

            holder.itemView.rv_month.run {
                val calendar = getCalendar(year, month)
                val startDOW = calendar.get(Calendar.DAY_OF_WEEK) - 2

                adapter = dayAdapter(year, month, startDOW, 42)
                layoutManager = GridLayoutManager(holder.itemView.context, 7)
            }

            holder.itemView.tv_week.setOnClickListener {
                val calendar = getCalendar(year, month)
//                Logger.e("get: "+ calendar.time.format(SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH)))
                calendar.set(Calendar.DAY_OF_WEEK, 1)
//                Logger.e("get: "+calendar.time.format(SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH)))

                onClickWeekMode(calendar)
            }
        }
    }

    fun dayAdapter(
        year: Int,
        month: Month,
        startDOW: Int,
        number: Int
    ) = object : FastAdapter<Any>(mutableListOf(), object : OnBindView() {
        override fun onBindView(holder: FastViewHolder, position: Int) {

            val calendar = Calendar.getInstance()

            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month.code)
            calendar.set(Calendar.DAY_OF_MONTH, position - startDOW)

            holder.itemView.tv_test.alpha =
                if (calendar.get(Calendar.MONTH) == month.code) 1f else 0.3f

            holder.itemView.tv_test.text = calendar.get(Calendar.DAY_OF_MONTH).toString()

//                    holder.itemView.tv_test.text = position.toString()
        }
    }, R.layout.item_day) {
        override fun getItemCount(): Int = number
    }

    fun dayAdapter2(
        calendar: Calendar,
        number: Int
    ) = object : FastAdapter<Any>(mutableListOf(), object : OnBindView() {
        override fun onBindView(holder: FastViewHolder, position: Int) {
            calendar.set(Calendar.DAY_OF_WEEK, position + 1)
            holder.itemView.tv_test.text = calendar.get(Calendar.DAY_OF_MONTH).toString()
        }
    }, R.layout.item_day) {
        override fun getItemCount(): Int = number
    }

    fun getCalendar(year: Int, month: Month): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month.code)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        return calendar
    }

    fun getCalendar(year: Int, week: Int): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.WEEK_OF_YEAR, week)
        calendar.set(Calendar.DAY_OF_WEEK, 2)
        return calendar
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    class Month(var name: String, var numberDay: Int, val code: Int)

    companion object {
        const val MONTH_MAX = 12
        const val FEB = 1

        val MONTHS = mutableListOf(
            Month("January", 31, 0),
            Month("February", 28, 1),
            Month("March", 31, 2),
            Month("April", 30, 3),
            Month("May", 31, 4),
            Month("June", 30, 5),
            Month("July", 31, 6),
            Month("August", 31, 7),
            Month("September", 30, 8),
            Month("October", 31, 9),
            Month("November", 30, 10),
            Month("December", 31, 11)
        )
    }
}
