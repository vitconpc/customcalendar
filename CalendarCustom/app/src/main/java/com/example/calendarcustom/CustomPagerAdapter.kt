package com.example.calendarcustom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_month.view.*
import java.util.*

class CustomPagerAdapter : RecyclerView.Adapter<CustomPagerAdapter.CustomViewHolder>() {

    //    private var currentPosition: Int = Calendar.getInstance().time.month
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_month, parent, false)
        )
    }

    override fun getItemCount(): Int = 2020 * MONTH_MAX + 9

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.onBind(position)
    }


    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int) {

            val year = (position / MONTH_MAX)
            val month = MONTHS[position % MONTH_MAX]
            itemView.tv_test.text =
                (year.toString() + "/" + month.name)
            itemView.recyclerview_month.run {
                val calendar = getCalendar(year, month)
                val startDOW = calendar.get(Calendar.DAY_OF_WEEK) - 2
                adapter = MonthAdapter(0.clone(42), year, month, startDOW)
                layoutManager = GridLayoutManager(itemView.context, 7)
            }
        }

        fun getCalendar(year: Int, month: Month): Calendar {
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month.code)
            calendar.set(Calendar.DAY_OF_MONTH, 1)
            return calendar
        }
    }

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

fun <T> T.clone(number: Int = 1000) = MutableList(number) { this }