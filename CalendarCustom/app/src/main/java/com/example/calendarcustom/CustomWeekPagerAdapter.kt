package com.example.calendarcustom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_week.view.*

class CustomWeekPagerAdapter :
    RecyclerView.Adapter<CustomWeekPagerAdapter.CustomWeekPagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomWeekPagerViewHolder {
        return CustomWeekPagerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_week, parent, false)
        )
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    override fun onBindViewHolder(holder: CustomWeekPagerViewHolder, position: Int) {
        holder.onBind(position)
    }

    inner class CustomWeekPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(position: Int) {
            itemView.recyclerview_week.run {
                adapter = WeekAdapter()
                layoutManager = GridLayoutManager(itemView.context, 7)
            }
        }

    }
}