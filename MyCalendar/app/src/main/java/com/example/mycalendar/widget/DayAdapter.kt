package com.example.mycalendar.widget

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mycalendar.R
import com.example.mycalendar.adapter.fast.FastViewHolder
import kotlinx.android.synthetic.main.item_month.view.*
import java.util.*

class DayAdapter(
    private val calendar: Calendar,
    private val number: Int,
    private val month: Int = 0,
    private val startDOW: Int = 0
) : RecyclerView.Adapter<FastViewHolder>() {
    val holders = mutableListOf<FastViewHolder>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FastViewHolder {
        return FastViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_day, parent, false)
        )
    }

    override fun getItemCount(): Int = number

    override fun onBindViewHolder(holder: FastViewHolder, position: Int) {
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