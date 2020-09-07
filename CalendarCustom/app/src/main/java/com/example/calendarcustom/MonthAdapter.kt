package com.example.calendarcustom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_day.view.*
import java.util.*

class MonthAdapter(
    var items: MutableList<Int>,
    val year: Int,
    val month: CustomPagerAdapter.Month,
    val startDOW: Int
) : RecyclerView.Adapter<MonthAdapter.MonthViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthViewHolder {
        return MonthViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_day, parent, false)
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MonthViewHolder, position: Int) {
        holder.onBind(position)
    }

    inner class MonthViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int) {
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month.code)
            calendar.set(Calendar.DAY_OF_MONTH, position - startDOW)
            itemView.text_day.text = calendar.get(Calendar.DAY_OF_MONTH).toString()
            itemView.text_day.alpha = if (calendar.get(Calendar.MONTH) == month.code) 1f else 0.3f
        }
    }
}