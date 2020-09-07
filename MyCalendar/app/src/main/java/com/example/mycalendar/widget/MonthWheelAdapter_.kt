//package com.rantea.animeowm.widget
//
//import androidx.recyclerview.widget.GridLayoutManager
//import com.example.mycalendar.R
//import com.example.mycalendar.utils.clone
//import com.rantea.animeowm.adapter.fast.FastAdapter
//import com.rantea.animeowm.adapter.fast.FastViewHolder
//import com.rantea.animeowm.adapter.fast.OnBindView
//import kotlinx.android.synthetic.main.item_month.view.*
//import java.util.*
//
//class MonthWheelAdapter_(val onClickWeekMode: (Calendar) -> Unit) : FastAdapter<Any>(
//    mutableListOf(), object : OnBindView() {
//        override fun onBindView(holder: FastViewHolder, position: Int) {}
//    }, R.layout.item_month
//) {
//
////    var midYear: Int = 2020
//    var weekMode: Boolean = false
//
//    override fun onBindViewHolder(holder: FastViewHolder, position: Int) {
//        super.onBindViewHolder(holder, position)
//        if (weekMode) {
//
//        } else {
//            val year = position / MONTH_MAX
//            val month = MONTHS[position % MONTH_MAX]
//            holder.itemView.tv_test.text = (year.toString() + "/" + month.name)
//
//            holder.itemView.rv_month.run {
//                val calendar = getCalendar(year, month)
//                val startDOW = calendar.get(Calendar.DAY_OF_WEEK) - 2
//
//                adapter = dayAdapter(year, month, startDOW)
//                layoutManager = GridLayoutManager(holder.itemView.context, 7)
//            }
//
//            holder.itemView.tv_week.setOnClickListener {
//                val calendar = getCalendar(year, month)
//                calendar.set(Calendar.DAY_OF_WEEK, 1)
//                onClickWeekMode(calendar)
//            }
//        }
//
//    }
//
//
//    fun dayAdapter(
//        year: Int,
//        month: Month,
//        startDOW: Int
//    ) = FastAdapter(0.clone(42), object : OnBindView() {
//        override fun onBindView(holder: FastViewHolder, position: Int) {
//
//            val calendar = Calendar.getInstance()
//
//            calendar.set(Calendar.YEAR, year)
//            calendar.set(Calendar.MONTH, month.code)
//            calendar.set(Calendar.DAY_OF_MONTH, position - startDOW)
//
//            holder.itemView.tv_test.alpha =
//                if (calendar.get(Calendar.MONTH) == month.code) 1f else 0.3f
//
//
//
//
//            holder.itemView.tv_test.text = calendar.get(Calendar.DAY_OF_MONTH).toString()
//
////                    holder.itemView.tv_test.text = position.toString()
//        }
//    }, R.layout.item_day)
//
//    fun getCalendar(year: Int, month: Month): Calendar {
//        val calendar = Calendar.getInstance()
//        calendar.set(Calendar.YEAR, year)
//        calendar.set(Calendar.MONTH, month.code)
//        calendar.set(Calendar.DAY_OF_MONTH, 1)
//        return calendar
//    }
//
//    override fun getItemCount(): Int = Int.MAX_VALUE
//
//    class Month(var name: String, var numberDay: Int, val code: Int)
//
//    companion object {
//        const val MONTH_MAX = 12
//        const val FEB = 1
//
//        val MONTHS = mutableListOf(
//            Month("January", 31, 0),
//            Month("February", 28, 1),
//            Month("March", 31, 2),
//            Month("April", 30, 3),
//            Month("May", 31, 4),
//            Month("June", 30, 5),
//            Month("July", 31, 6),
//            Month("August", 31, 7),
//            Month("September", 30, 8),
//            Month("October", 31, 9),
//            Month("November", 30, 10),
//            Month("December", 31, 11)
//        )
//    }
//}
