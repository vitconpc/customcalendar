package com.rantea.animeowm.widget

import com.example.mycalendar.R
import com.rantea.animeowm.adapter.fast.FastAdapter
import com.rantea.animeowm.adapter.fast.FastViewHolder
import com.rantea.animeowm.adapter.fast.OnBindView
import kotlinx.android.synthetic.main.item_month.view.*
import java.util.*

class DayAdapter(
    val calendar: Calendar,
    val number: Int,
    val month: Int = 0,
    val startDOW: Int = 0
) : FastAdapter<Any>(mutableListOf(), object : OnBindView() {
    override fun onBindView(holder: FastViewHolder, position: Int) {}
}, R.layout.item_day) {
    val holders = mutableListOf<FastViewHolder>()

    override fun getItemCount(): Int = number

    override fun onBindViewHolder(holder: FastViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        if (!holders.contains(holder)) {
            holder.itemView.tag = position
            holders.add(holder)
        }
        if (number != WEEK_MODE_NUMBER) {
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, position - startDOW)
            holder.itemView.tv_test.text = calendar.get(Calendar.DAY_OF_MONTH).toString()

            holder.itemView.tv_test.alpha =
                if (calendar.get(Calendar.MONTH) == month) 1f else 0.3f
        } else {
            calendar.set(Calendar.DAY_OF_WEEK, position + 1)
            holder.itemView.tv_test.text = calendar.get(Calendar.DAY_OF_MONTH).toString()
        }
    }

    companion object {
        const val WEEK_MODE_NUMBER = 7
    }
}